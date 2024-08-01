package lion6.DrinkGuide.api.member.dto.response;

import lion6.DrinkGuide.api.member.domain.SubscribeType;
import lion6.DrinkGuide.common.util.TimeUtil;

import java.time.LocalDateTime;

public record MemberSubscribeGetResponseDto(
        String subscribeType,
        String expirationDate
) {
    public static MemberSubscribeGetResponseDto of(SubscribeType subscribeType, LocalDateTime expirationDate) {
        return new MemberSubscribeGetResponseDto(
                subscribeType.getKey(),
                TimeUtil.refineDate(expirationDate));
    }
}
