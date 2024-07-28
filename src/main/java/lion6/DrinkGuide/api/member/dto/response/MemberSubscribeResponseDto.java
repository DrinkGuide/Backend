package lion6.DrinkGuide.api.member.dto.response;

public record MemberSubscribeResponseDto(
        Boolean isSubscribe
) {
    public static MemberSubscribeResponseDto of(Boolean isSubscribe) { return new MemberSubscribeResponseDto(isSubscribe); }
}
