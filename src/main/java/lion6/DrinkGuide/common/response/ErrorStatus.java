package lion6.DrinkGuide.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

public enum ErrorStatus {
    /**
     * 400 BAD_REQUEST
     */
    VALIDATION_EXCEPTION("잘못된 요청입니다."),
    INVALID_REQUEST_PARAMETER("Request Parameter 값이 입력되지 않았습니다."),
    INVALID_REQUEST_BODY("Request Body가 누락됐거나 올바르지 않은 형식입니다."),
    NO_TOKEN("토큰을 넣어주세요."),
    INVALID_MEMBER("유효하지 않은 유저입니다."),
    ANOTHER_ACCESS_TOKEN("지원하지 않는 소셜 플랫폼입니다."),
    DUPLICATION_CONTENT_LIKE("이미 좋아요를 누른 게시물입니다."),
    UNEXITST_CONTENT_LIKE("좋아요를 누르지 않은 게시물입니다."),
    GHOST_HIGHLIMIT("투명도는 0이상일 수 없습니다."),
    DUPLICATION_COMMENT_LIKE("이미 좋아요를 누른 답글입니다."),
    UNEXITST_CONMMENT_LIKE("좋아요를 누르지 않은 답글입니다."),
    DUPLICATION_MEMBER_GHOST("이미 투명도를 누른 대상입니다."),
    NICKNAME_VALIDATE_ERROR("이미 존재하는 닉네임입니다."),
    GHOST_MYSELF_BLOCK("본인의 투명도를 내릴 수 없습니다."),
    GHOST_USER("투명도가 -85이하라서 글이나 답글을 작성할 수 없습니다."),
    WITHDRAWAL_MEMBER("계정 삭제 후 30일 이내 회원입니다."),
    UNVALID_PROFILEIMAGE_TYPE("이미지 확장자는 jpg, png, webp만 가능합니다."),
    PROFILE_IMAGE_DATA_SIZE("이미지 사이즈는 5MB를 넘을 수 없습니다."),


    /**
     * 401 UNAUTHORIZED
     */
    UNAUTHORIZED_MEMBER("권한이 없는 유저입니다."),
    UNAUTHORIZED_TOKEN("유효하지 않은 토큰입니다."),
    KAKAO_UNAUTHORIZED_USER("카카오 로그인 실패. 만료되었거나 잘못된 카카오 토큰입니다."),
    FAILED_TO_VALIDATE_APPLE_LOGIN("애플 로그인 실패. 만료되었거나 잘못된 애플 토큰입니다."),
    SIGNIN_REQUIRED("엑세스 토큰 및 리프레쉬 토큰 모두 만료되었습니다. 재로그인이 필요합니다."),
    VALID_ACCESS_TOKEN("아직 유효한 accessToken 입니다."),

    EMPTY_JWT("JWT가 비어있습니다."),
    EXPIRED_ACCESS("엑세스 토큰이 만료됐습니다."),
    UNSUPPORTED_TOKEN("지원하지 않는 형식의 토큰입니다."),
    INVALID_SIGNATURE("유효하지 않은 서명입니다."),
    INVALID_TOKEN("유효하지 않은 뒷부분입니다."),

    /**
     * 404 NOT_FOUND
     */
    NOT_FOUND_MEMBER("해당하는 유저가 없습니다."),
    NOT_FOUND_PAYMENTS_HISTORY("진행중인 결제 기록이 없습니다."),
    NOT_FOUND_PRODUCT("해당하는 제품이 없습니다."),


    /**
     * 405 METHOD_NOT_ALLOWED
     */
    INVALID_PATHVARIABLE_HTTTPMETHOD("요청 URL이 잘못됐습니다. - HTTP Method 또는 Path Variable을 확인해주세요"),

    /**
     * 500 SERVER_ERROR
     */
    INTERNAL_SERVER_ERROR("예상치 못한 서버 에러가 발생했습니다."),
    BAD_GATEWAY_EXCEPTION("일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!");

    private final String message;

}
