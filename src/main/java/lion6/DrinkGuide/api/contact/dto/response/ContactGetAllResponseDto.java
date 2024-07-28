package lion6.DrinkGuide.api.contact.dto.response;

import lion6.DrinkGuide.common.util.TimeUtil;

import java.time.LocalDateTime;

public record ContactGetAllResponseDto(
        String email,
        String title,
        String content,
        String time

) {
    public static ContactGetAllResponseDto of(String email, String title, String content, LocalDateTime time) {
        return new ContactGetAllResponseDto(
                email,
                title,
                content,
                TimeUtil.refineTime(time)
        );
    }
}
