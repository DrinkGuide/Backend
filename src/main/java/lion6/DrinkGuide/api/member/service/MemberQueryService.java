package lion6.DrinkGuide.api.member.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.dto.response.MemberGetResponseDto;
import lion6.DrinkGuide.api.member.dto.response.MemberSubscribeGetResponseDto;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberGetResponseDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        return MemberGetResponseDto.of(member.getName(), member.getSubscribeType().getKey());
    }

    public MemberSubscribeGetResponseDto getMemberSubscribe(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        return MemberSubscribeGetResponseDto.of(member.getSubscribeType(), member.getExpirationDate());
    }

}
