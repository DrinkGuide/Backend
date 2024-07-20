package lion6.DrinkGuide.common.advice;

import jakarta.servlet.http.HttpServletRequest;
import lion6.DrinkGuide.common.exception.BaseException;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**c
     * 404 / 401
     * BaseException 예외를 처리 - (NotFound / UnAuthroized)
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleGlobalException(BaseException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ApiResponse.fail(ex.getStatusCode(), ex.getResponseMessage()));
    }

    /**
     * 400
     * 요청 본문이 읽을 수 없는 경우의 예외 처리
     * Request Body 자체의 누락 또는 JSON이 잘못된 형태인 경우
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ErrorStatus.INVALID_REQUEST_BODY.getMessage()));
    }
    /**
     * 400
     * @Valid 결과 유효하지 않다고 판단된 경우 예외 처리
     * Request Body 자체는 넘어왔지만 안에 값들의 @Valid 검증 중 유효하지 않은 필드값 발생 시
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(),String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField())));
    }

    /**
     * 400
     * URL에 Request Parameter가 누락된 경우
     * http://localhost:8080? 뒤에 누락
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleMissingParameter(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(),
                        ErrorStatus.INVALID_REQUEST_PARAMETER.getMessage()));
    }

    /**
     * 405
     * API 의 Request URL 을 잘못 입력하여 매칭 안된 경우(PathVariable 누락 시도 포함)
     * HTTP 메서드가 잘못 선택된 경우
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), ErrorStatus.INVALID_PATHVARIABLE_HTTTPMETHOD.getMessage()));
    }

    /**
     * 사실 따로 처리해줘서 필요없음 - 잘못된 인자가 전달 됐을 시 예외 처리
     * find___OrThrow로 처리해주기 때문에 노상관
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }



    /**
     * 500 Internal Server Error 예외 처리
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse> handleException(Exception ex, final Exception error, final HttpServletRequest request) throws IOException {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }


}

