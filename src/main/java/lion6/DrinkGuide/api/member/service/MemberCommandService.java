package lion6.DrinkGuide.api.member.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.domain.RoleType;
import lion6.DrinkGuide.api.member.dto.response.TokenReissueResponseDto;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.common.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public void updateRefreshToken(Long memberId, String refreshToken) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);
    }

    public TokenReissueResponseDto reissueAccessToken(String refreshToken) {
        jwtUtil.validateToken(refreshToken);
        memberRepository.findMemberByRefreshTokenOrThrow(refreshToken);

        Long memberIdRefresh = jwtUtil.getMemberId(refreshToken);
        RoleType roleRefresh = jwtUtil.getRole(refreshToken);

        String newAccessToken = jwtUtil.generateToken("access", memberIdRefresh, String.valueOf(roleRefresh), jwtUtil.accessTokenExpireLength);

        return TokenReissueResponseDto.of(newAccessToken);
    }

    public void updateUiType(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        member.updateUiType();
    }

}
