package lion6.DrinkGuide.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {
    /**
     * Member
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    GET_NEW_TOKEN_SUCCESS(HttpStatus.OK,"토큰 재발급 성공"),
    WITHDRAWAL_SUCCESS(HttpStatus.OK,"유저 탈퇴 성공"),
    GET_IS_SUBSCRIBE_SUCCESS(HttpStatus.OK, "구독 여부 조회 성공"),
    UPDATE_UI_TYPE_SUCCESS(HttpStatus.OK, "UI Type 업데이트 성공"),

    /**
     * (Toss) Payments History
     */
    INITIALIZE_PAYMENTS_SUCCESS(HttpStatus.CREATED, "토스 페이먼츠 초기화 성공"),
    PAYMENTS_APPROVAL_SUCCESS(HttpStatus.OK, "토스 페이먼츠 결제 승인 성공"),

    /**
     * Contact
     */
    GET_CONTACTS_SUCCESS(HttpStatus.OK, "문의 조회 성공"),
    CREATE_CONTACT_SUCCESS(HttpStatus.CREATED, "문의 등록 성공")
    ;
    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}

