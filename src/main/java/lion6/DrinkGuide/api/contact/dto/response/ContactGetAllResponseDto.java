package lion6.DrinkGuide.api.contact.dto.response;

import lion6.DrinkGuide.api.contact.domain.Contact;
import lion6.DrinkGuide.common.util.TimeUtil;

import java.time.LocalDateTime;

public record ContactGetAllResponseDto(
        String email,
        String title,
        String content,
        String time

) {
    public static ContactGetAllResponseDto of(Contact contact) {
        return new ContactGetAllResponseDto(
                contact.getMember().getEmail(),
                contact.getTitle(),
                contact.getContent(),
                TimeUtil.refineTime(contact.getCreatedDate())
        );
    }
}
