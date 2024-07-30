package lion6.DrinkGuide.api.payments.repository;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.payments.domain.PaymentsHistory;
import lion6.DrinkGuide.common.exception.NotFoundException;
import lion6.DrinkGuide.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static lion6.DrinkGuide.common.response.ErrorStatus.NOT_FOUND_PAYMENTS_HISTORY;

public interface PaymentsRepostory extends JpaRepository<PaymentsHistory, Long> {

    Optional<PaymentsHistory> findPaymentsHistoryByOrderId(String orderId);

    default PaymentsHistory findPaymentsHistoryByOrderIdOrThrow(String orderId) {
        return findPaymentsHistoryByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_PAYMENTS_HISTORY.getMessage()));
    }

}
