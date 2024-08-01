package lion6.DrinkGuide.api.member.dto.response;

import lion6.DrinkGuide.api.member.domain.SubscribeType;
import lion6.DrinkGuide.common.util.TimeUtil;

import java.time.LocalDateTime;

public record MemberNotSubscribeGetResponseDto(
        String subscribeType
) {
    public static MemberNotSubscribeGetResponseDto of(SubscribeType subscribeType) {
        return new MemberNotSubscribeGetResponseDto(
                subscribeType.getKey()
        );
    }
}
