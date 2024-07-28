package lion6.DrinkGuide.api.member.dto.request;


import jakarta.validation.constraints.NotBlank;

public record TokenReissueRequestDto(
        @NotBlank String refreshToken
){
}
