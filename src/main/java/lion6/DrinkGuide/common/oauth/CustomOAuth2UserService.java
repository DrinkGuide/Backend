package lion6.DrinkGuide.common.oauth;

import lion6.DrinkGuide.api.Member.domain.Member;
import lion6.DrinkGuide.api.Member.domain.RoleType;
import lion6.DrinkGuide.api.Member.repository.MemberRepository;
import lion6.DrinkGuide.common.response.oauth.GoogleResponse;
import lion6.DrinkGuide.common.response.oauth.OAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        OAuth2Response oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        String provider = oAuth2Response.getProvider();
        String providerId = oAuth2Response.getProviderId();
        String name = oAuth2Response.getName();
        String email = oAuth2Response.getEmail();
        Optional<Member> memberByProviderAndProviderId = memberRepository.findMemberByProviderAndProviderId(provider, providerId);
        Member savedMember = memberByProviderAndProviderId.orElseGet(() -> saveNewMember(provider, providerId, name, email));

        return new CustomOAuth2User(savedMember);
    }

    private Member saveNewMember(String provider, String providerId, String name, String email) {
        Member newMember = Member.builder()
                .provider(provider)
                .providerId(providerId)
                .name(name)
                .email(email)
                .roleType(RoleType.USER)
                .build();
        return memberRepository.save(newMember);
    }
}