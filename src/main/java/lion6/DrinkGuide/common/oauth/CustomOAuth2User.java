package lion6.DrinkGuide.common.oauth;

import lion6.DrinkGuide.api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private final Member member;
    public Long getMemberId() { return member.getId(); }

    @Override
    public String getName() {
        return member.getName();
    }
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRoleType().getKey();
            }
        });
        return collection;
    }

}
