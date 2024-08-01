package lion6.DrinkGuide.api.member.dto.response;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.domain.SubscribeType;
import lion6.DrinkGuide.common.util.TimeUtil;

import java.time.LocalDateTime;

public record MemberSubscribeGetResponseDto(
        String subscribeType,
        String expirationDate
) {
    public static MemberSubscribeGetResponseDto of(Member member) {
        // 구독 타입을 문자열로 가져옴
        String subscribeType = member.getSubscribeType().getKey();
        // 만료 날짜가 null인지 확인
        String expirationDate;
        if (subscribeType == SubscribeType.NONE.getKey()) {
            expirationDate = "구독자가 아니라 만료 시점이 존재하지 않습니다."; // 만료 날짜가 없는 경우
        } else {
            expirationDate = TimeUtil.refineDate(member.getExpirationDate()); // 만료 날짜 형식화
        }

        return new MemberSubscribeGetResponseDto(subscribeType, expirationDate);
    }
}
