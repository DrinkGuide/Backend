package lion6.DrinkGuide.api.purchase.domain;

import jakarta.persistence.*;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.common.config.auditing.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 어떤 음료 샀는지
     */
}
