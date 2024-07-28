package lion6.DrinkGuide.api.payments.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentRequest {
    private String paymentKey;
    private String orderId;
    private int amount;
    private Long memberId;
}