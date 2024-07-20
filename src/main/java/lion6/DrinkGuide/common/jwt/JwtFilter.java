package lion6.DrinkGuide.common.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lion6.DrinkGuide.api.Member.domain.Member;
import lion6.DrinkGuide.api.Member.domain.RoleType;
import lion6.DrinkGuide.common.oauth.CustomOAuth2User;
import lion6.DrinkGuide.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final JwtEntryPoint jwtEntryPoint;

    private static final String[] WHITELIST = {
            "/login",
            "/api/v1/health",
            // Swagger URL
            "/swagger-resources/**",
            "/favicon.ico",
            "/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/docs/swagger-ui/index.html",
            "/swagger-ui/swagger-ui.css",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs/**"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        return PatternMatchUtils.simpleMatch(WHITELIST, requestURI);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.parseToken(request);
        JwtExceptionType validateResult = jwtUtil.validateToken(accessToken);

        if (validateResult == JwtExceptionType.VALID_JWT_TOKEN) {
            Long memberId = jwtUtil.getMemberId(accessToken);
            RoleType role = jwtUtil.getRole(accessToken);

            Member jwtInfo = Member.builder()
                    .roleType(role)
                    .build();
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(jwtInfo);
            Authentication authentication = new UsernamePasswordAuthenticationToken(memberId, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            ErrorStatus errorStatus;
            switch (validateResult) {
                case EMPTY_JWT:
                    errorStatus = ErrorStatus.EMPTY_JWT;
                    break;
                case EXPIRED_JWT_TOKEN:
                    errorStatus = ErrorStatus.EXPIRED_ACCESS;
                    break;
                case INVALID_JWT_TOKEN:
                    errorStatus = ErrorStatus.INVALID_TOKEN;
                    break;
                case UNSUPPORTED_JWT_TOKEN:
                    errorStatus = ErrorStatus.UNSUPPORTED_TOKEN;
                    break;
                case INVALID_JWT_SIGNATURE:
                    errorStatus = ErrorStatus.INVALID_SIGNATURE;
                    break;
                default:
                    errorStatus = ErrorStatus.INTERNAL_SERVER_ERROR;
            }
            jwtEntryPoint.setResponse(response, errorStatus);
        }
    }
}
