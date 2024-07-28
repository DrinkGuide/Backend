package lion6.DrinkGuide.api.payments.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentsHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String paymentKey;

    @Column(nullable = false)
    private int amount;

    @Builder
    public PaymentsHistory(String paymentType, String orderId, String paymentKey, int amount) {
        this.paymentType = paymentType;
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.amount = amount;
    }
}
