package lion6.DrinkGuide.api.Member.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, name = "provider_id")
    private String providerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "refresh_token", length = 500) // refresh_token 속성의 최대 길이를 300으로 지정
    private String refreshToken;

    @Column(nullable = true)
    private String nickname;

    @Column(nullable = true, name = "member_intro")
    private String memberIntro;

    @Builder // OAuth2.0 로그인 시 자동 입력되는 정보
    private Member(String provider, String providerId, String name, String email, RoleType roleType){
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }

    // Setter를 쓰지않고 업데이트 메소드 활용
    public void updateMemberProfile(String nickname, String memberIntro) {
        this.nickname = nickname;
        this.memberIntro = memberIntro;
    }
    public void updateRefreshToken(String refreshToken) { this.refreshToken = refreshToken;}

}

