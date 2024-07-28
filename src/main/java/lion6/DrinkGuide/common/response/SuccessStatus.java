package lion6.DrinkGuide.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {
    /**
     * member
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    GET_NEW_TOKEN_SUCCESS(HttpStatus.OK,"토큰 재발급 성공"),
    WITHDRAWAL_SUCCESS(HttpStatus.OK,"유저 탈퇴 성공"),
    GET_IS_SUBSCRIBE_SUCCESS(HttpStatus.OK, "구독 여부 조회 성공"),
    UPDATE_UI_TYPE_SUCCESS(HttpStatus.OK, "UI Type 업데이트 성공"),

    /**
     * contact
     */
    CREATE_CONTACT_SUCCESS(HttpStatus.CREATED, "문의 등록 성공")
    ;
    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}

