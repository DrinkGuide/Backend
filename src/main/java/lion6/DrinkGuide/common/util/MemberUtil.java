package lion6.DrinkGuide.common.util;

import lion6.DrinkGuide.common.exception.UnAuthorizedException;
import lion6.DrinkGuide.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class MemberUtil {
    public static Long getMemberId(Principal principal) {
        // Principal 객체가 null이면 사용자가 인증되지 않았으므로 예외를 발생시킨다.
        if (principal == null) {
            throw new UnAuthorizedException(ErrorStatus.INVALID_MEMBER.getMessage());
        }
        // Principal 객체의 이름을 memberId ID로 사용하여 반환한다.
        return Long.valueOf(principal.getName());
    }
}


