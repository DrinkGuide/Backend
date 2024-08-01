package lion6.DrinkGuide.api.payments.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.domain.SubscribeType;
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
public class PaymentsHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = true)
    private String paymentKey;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscribeType subscribeType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Builder
    public PaymentsHistory(Member member, String orderId, int amount, String subscribeType) {
        this.member = member;
        this.orderId = orderId;
        this.amount = amount;
        this.subscribeType = SubscribeType.valueOf(subscribeType);
    }

    public void approvePaymentsHistory(String paymentKey) {
        this.paymentKey = paymentKey;
        this.paymentStatus = PaymentStatus.APPROVED;
    }
}
