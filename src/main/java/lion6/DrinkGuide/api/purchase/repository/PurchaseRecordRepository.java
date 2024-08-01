package lion6.DrinkGuide.api.purchase.repository;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findAllByMemberOrderByCreatedDate(Member member);
}
