package lion6.DrinkGuide.api.member.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.api.payments.domain.PaymentsHistory;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, name = "provider_id")
    private String providerId;

    @Column(nullable = false)
    private String email;

    private LocalDateTime expirationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "refresh_token", length = 500) // refresh_token 속성의 최대 길이를 300으로 지정
    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscribeType subscribeType = SubscribeType.DRINK_SNACK; // 무료 체험 1주일

    @Column(name = "ui_type", nullable = false)
    private Boolean UiType = false; // false: 어둡게, true: 밝게

    @OneToMany(mappedBy = "member")
    private List<PaymentsHistory> paymentsHistories = new ArrayList<>();

    @Builder // OAuth2.0 로그인 시 자동 입력되는 정보
    private Member(String provider, String providerId, String name, String email, RoleType roleType){
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }
    public void subscribe(SubscribeType subscribeType) {
        this.subscribeType = subscribeType;
        this.expirationDate = LocalDateTime.now()
                .plusMonths(1)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0);
    }
    public void updateUiType() { this.UiType = !this.UiType; }
    public void updateRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}
}

