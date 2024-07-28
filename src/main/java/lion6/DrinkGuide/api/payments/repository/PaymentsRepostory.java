package lion6.DrinkGuide.api.payments.repository;

import lion6.DrinkGuide.api.payments.domain.PaymentsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepostory extends JpaRepository<PaymentsHistory, Long> {

}
