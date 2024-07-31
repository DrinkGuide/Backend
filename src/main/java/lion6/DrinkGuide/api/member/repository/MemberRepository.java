package lion6.DrinkGuide.api.member.repository;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.common.exception.NotFoundException;
import lion6.DrinkGuide.common.exception.UnAuthorizedException;
import lion6.DrinkGuide.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);
    default Member findMemberByIdOrThrow(Long memberId) {
        return findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
    Optional<Member> findMemberByProviderAndProviderId(String provider, String providerId);

    Optional<Member> findMemberByRefreshToken(String refreshToken);
    default Member findMemberByRefreshTokenOrThrow(String refreshToken) {
        return findMemberByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }

    @Query("SELECT m FROM Member m WHERE m.id = :memberId AND m.roleType = 'ADMIN'")
    Optional<Member> findMemberByIdRoleType(@Param("memberId") Long memberId);

    default Member findMemberByIdRoleTypeOrThrow(Long memberId) {
        return findMemberByIdRoleType(memberId)
                .orElseThrow(() -> new UnAuthorizedException(ErrorStatus.UNAUTHORIZED_MEMBER.getMessage()));
    }
}
