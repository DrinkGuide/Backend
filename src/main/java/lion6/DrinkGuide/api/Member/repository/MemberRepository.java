package lion6.DrinkGuide.api.Member.repository;

import lion6.DrinkGuide.api.Member.domain.Member;
import lion6.DrinkGuide.common.exception.NotFoundException;
import lion6.DrinkGuide.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);
    default Member findMemberByIdOrThrow(Long memberId) {
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
    Optional<Member> findMemberByProviderAndProviderId(String provider, String providerId);
}
