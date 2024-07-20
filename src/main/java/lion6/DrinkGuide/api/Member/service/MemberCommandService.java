package lion6.DrinkGuide.api.Member.service;

import lion6.DrinkGuide.api.Member.domain.Member;
import lion6.DrinkGuide.api.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    public void updateRefreshToken(Long memberId, String refreshToken) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);
    }
}
