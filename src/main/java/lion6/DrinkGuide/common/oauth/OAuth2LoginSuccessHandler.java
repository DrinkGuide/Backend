package lion6.DrinkGuide.common.oauth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lion6.DrinkGuide.api.Member.service.MemberCommandService;
import lion6.DrinkGuide.common.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberCommandService memberCommandService;
    private final JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
        Long memberId = principal.getMemberId();
        String authorities = principal.getAuthorities().toString();
        String accessToken = jwtUtil.generateToken("access", memberId, authorities, jwtUtil.accessTokenExpireLength);
        String refreshToken = jwtUtil.generateToken("refresh", memberId, authorities, jwtUtil.refreshTokenExpireLength);

        response.addCookie(createCookie("access", accessToken));
        response.addCookie(createCookie("refresh", refreshToken));

        memberCommandService.updateRefreshToken(memberId, refreshToken);
        response.sendRedirect(jwtUtil.JWT_REDIRECT);
    }
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
//        cookie.setHttpOnly(true); // JS가 가져 가지 못하게(XSS 방지)

        return cookie;
    }
}