package lion6.DrinkGuide.api.purchase.repository;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findAllByMemberOrderByCreatedDate(Member member);

    @Query("SELECT pr.productType, COUNT(pr) FROM PurchaseRecord pr " +
            "WHERE pr.member = :member AND FUNCTION('MONTH', pr.createdDate) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', pr.createdDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "GROUP BY pr.productType")
    List<Object[]> countByProductTypeAndMember(@Param("member") Member member);
}
