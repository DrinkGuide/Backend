package lion6.DrinkGuide.api.purchase.repository;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.purchase.domain.ProductType;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findAllByMemberOrderByCreatedDate(Member member);

    @Query(value = "SELECT pr.productType FROM PurchaseRecord pr " +
            "WHERE MONTH(pr.createdDate) = MONTH(NOW()) " +
            "AND YEAR(pr.createdDate) = YEAR(NOW()) " +
            "AND pr.member.id = :memberId " +
            "ORDER BY pr.createdDate DESC")
    List<ProductType> findRecentProductTypesByMemberId(@Param("memberId") Long memberId);

}
