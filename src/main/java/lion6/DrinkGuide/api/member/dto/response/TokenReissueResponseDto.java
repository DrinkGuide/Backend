package lion6.DrinkGuide.api.member.dto.response;

public record TokenReissueResponseDto(
        String accessToken
) {
    public static TokenReissueResponseDto of(String accessToken) { return new TokenReissueResponseDto(accessToken); }
}
