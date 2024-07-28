package lion6.DrinkGuide.api.member.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, name = "provider_id")
    private String providerId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isSubscribe = false; // 기본값 false (구독X)

    private LocalDateTime expirationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "refresh_token", length = 500) // refresh_token 속성의 최대 길이를 300으로 지정
    private String refreshToken;

    @Column(name = "ui_type", nullable = false)
    private Boolean UiType = false; // false: 어둡게, true: 밝게

    @Builder // OAuth2.0 로그인 시 자동 입력되는 정보
    private Member(String provider, String providerId, String name, String email, RoleType roleType){
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }
    public void subscribe() {
        this.isSubscribe = true;
        this.expirationDate = LocalDateTime.now().plusMonths(1);
    }
    public void updateUiType() { this.UiType = !this.UiType; }
    public void updateRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}



}

