package lion6.DrinkGuide.common.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lion6.DrinkGuide.api.Member.domain.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${spring.jwt.expire-length.access-expire}")
    public Long accessTokenExpireLength;
    @Value("${spring.jwt.expire-length.refresh-expire}")
    public Long refreshTokenExpireLength;
    @Value("${spring.jwt.redirect-url}")
    public String JWT_REDIRECT;
    private SecretKey secretKey;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    public JwtUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /**
     * 토큰 생성
     */
    public String generateToken(String category, Long memberId, String role, Long expiredMs) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiredMs * 1000);

        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    /**
     * Token 파싱
     */
    public String parseToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        } else {
            return header.split(" ")[1];
        }
    }

    /**
     * 토큰 유효성 검증
     */
    public JwtExceptionType validateToken(String token) {
        try {
            // JWT 파서를 생성하고, parseClaimsJws(token) 메소드로 토큰의 유효성을 검증
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return JwtExceptionType.VALID_JWT_TOKEN;
        } catch (io.jsonwebtoken.security.SignatureException exception) {
            log.error("잘못된 JWT 서명을 가진 토큰입니다.");
            return JwtExceptionType.INVALID_JWT_SIGNATURE;
        } catch (MalformedJwtException exception) {
            log.error("잘못된 JWT 토큰입니다.");
            return JwtExceptionType.INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException exception) {
            log.error("만료된 JWT 토큰입니다.");
            return JwtExceptionType.EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException exception) {
            log.error("지원하지 않는 JWT 토큰입니다.");
            return JwtExceptionType.UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException exception) {
            log.error("JWT Claims가 비어있습니다.");
            return JwtExceptionType.EMPTY_JWT;
        }
    }

    /**
     * memberId 추출
     */
    public Long getMemberId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("memberId", Long.class);
    }

    /**
     * role 추출
     */
    public RoleType getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", RoleType.class);
    }

}
