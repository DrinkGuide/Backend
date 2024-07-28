package lion6.DrinkGuide.api.member.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.dto.response.MemberSubscribeResponseDto;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberSubscribeResponseDto getIsSubscribe(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        return MemberSubscribeResponseDto.of(member.getIsSubscribe());
    }

}
