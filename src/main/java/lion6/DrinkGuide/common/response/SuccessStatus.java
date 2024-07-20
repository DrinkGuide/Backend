package lion6.DrinkGuide.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {
    /**
     * auth
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    GET_NEW_TOKEN_SUCCESS(HttpStatus.OK,"토큰 재발급 성공"),
    WITHDRAWAL_SUCCESS(HttpStatus.OK,"유저 탈퇴 성공"),

    /**
     * chat
     */
    CREATE_THREAD_SUCCESS(HttpStatus.CREATED, "쓰레드 생성 성공"),
    GET_THREADS_SUCCESS(HttpStatus.OK, "쓰레드들 조회 성공"),
    GET_THREAD_SUCCESS(HttpStatus.OK, "채팅방 조회 성공"),
    CREATE_MESSAGE_SUCCESS(HttpStatus.CREATED, "메세지 생성 성공"),

    /**
     * content
     */
    CONTENT_LIKE_SUCCESS(HttpStatus.CREATED, "게시물 좋아요 성공"),
    GET_CONTENT_DETAIL_SUCCESS(HttpStatus.OK, "게시물 상세 조회 성공"),
    POST_CONTENT_SUCCESS(HttpStatus.CREATED,"게시글 작성 성공"),
    DELETE_CONTENT_SUCCESS(HttpStatus.OK,"게시글 삭제 성공"),
    CONTENT_UNLIKE_SUCCESS(HttpStatus.OK,"게시글 좋아요 취소 성공"),
    GET_CONTENT_ALL_SUCCESS(HttpStatus.OK, "게시물 리스트 조회 성공"),
    GET_MEMBER_CONTENT_SUCCESS(HttpStatus.OK,"유저에 해당하는 게시글 리스트 조회"),

    /**
     * comment
     */
    POST_COMMENT_SUCCESS(HttpStatus.CREATED,"답글 작성 성공"),
    DELETE_COMMENT_SUCCESS(HttpStatus.OK, "답글 삭제 성공"),
    COMMENT_LIKE_SUCCESS(HttpStatus.CREATED,"답글 좋아요 성공"),
    COMMENT_UNLIKE_SUCCESS(HttpStatus.OK,"답글 좋아요 취소 성공"),
    GET_COMMENT_ALL_SUCCESS(HttpStatus.OK,"게시물에 해당하는 답글 리스트 조회 성공"),
    GET_MEMBER_COMMENT_SECCESS(HttpStatus.OK,"멤버에 해당하는 답글 리스트 조회 성공"),

    /**
     * member
     */
    GET_MEMBER_DETAIL(HttpStatus.OK,"계정 정보 조회 성공"),
    GET_PROFILE_SUCCESS(HttpStatus.OK,"유저 프로필 조회 성공"),
    SAVE_MEMBER_PROFILE(HttpStatus.OK, "유저 프로필 저장 성공"),
    PATCH_MEMBER_PROFILE(HttpStatus.OK, "프로필 수정 완료"),
    NICKNAME_CHECK_SUCCESS(HttpStatus.OK, "사용 가능한 닉네임 입니다."),
    NOTIFICATION_ALL_SUCCESS(HttpStatus.OK,"알림 전체 조회 성공"),

    /**
     * TradeHistory
     */
    SAVE_TRADE_HISTORY_SUCCESS(HttpStatus.CREATED, "거래 내역 저장 성공"),
    GET_TRADE_HISTORY_SUCCESS(HttpStatus.OK, "거래 내역 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}

