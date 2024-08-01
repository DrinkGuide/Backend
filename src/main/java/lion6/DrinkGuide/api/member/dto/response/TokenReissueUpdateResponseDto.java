package lion6.DrinkGuide.api.member.dto.response;

public record TokenReissueUpdateResponseDto(
        String accessToken
) {
    public static TokenReissueUpdateResponseDto of(String accessToken) { return new TokenReissueUpdateResponseDto(accessToken); }
}
