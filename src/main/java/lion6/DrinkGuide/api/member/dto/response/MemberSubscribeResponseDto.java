package lion6.DrinkGuide.api.member.dto.response;

public record MemberSubscribeResponseDto(
        String nickname,
        String subscribeType

        // TODO :: 구매 인증 횟수 추가
) {
    public static MemberSubscribeResponseDto of(String nickname, String subscribeType) {
        return new MemberSubscribeResponseDto(nickname, subscribeType);
    }
}
