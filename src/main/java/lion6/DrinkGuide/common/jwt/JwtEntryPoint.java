package lion6.DrinkGuide.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.response.ErrorStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT Authentication시 예외처리용
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        setResponse(response, ErrorStatus.UNAUTHORIZED_TOKEN);
    }


    public void setResponse(HttpServletResponse response, ErrorStatus status) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse apiResponse = ApiResponse.fail(response.getStatus(), status.getMessage());
        response.getWriter().println(mapper.writeValueAsString(apiResponse));
    }
}
