package lion6.DrinkGuide.api.purchase.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PurchaseRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String productName; // 제품이름

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Builder
    public PurchaseRecord(Member member, String productName, String productType) {
        this.member = member;
        this.productName = productName;
        this.productType = ProductType.valueOf(productType);
    }
}
