package lion6.DrinkGuide.common.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends BaseException{

    public UnAuthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(String responseMessage) {
        super(HttpStatus.UNAUTHORIZED, responseMessage);
    }
}
