package lion6.DrinkGuide.api.member.dto.response;

public record MemberGetResponseDto(
        String nickname,
        String subscribeType

        // TODO :: 구매 인증 횟수 추가
) {
    public static MemberGetResponseDto of(String nickname, String subscribeType) {
        return new MemberGetResponseDto(nickname, subscribeType);
    }
}
