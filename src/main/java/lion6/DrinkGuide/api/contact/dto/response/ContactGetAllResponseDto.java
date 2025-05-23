package lion6.DrinkGuide.api.contact.dto.response;

import lion6.DrinkGuide.api.contact.domain.Contact;
import lion6.DrinkGuide.common.util.TimeUtil;

public record ContactGetAllResponseDto(
        String email,
        String content,
        String time

) {
    public static ContactGetAllResponseDto of(Contact contact) {
        return new ContactGetAllResponseDto(
                contact.getMember().getEmail(),
                contact.getContent(),
                TimeUtil.refineTime(contact.getCreatedDate())
        );
    }
}
