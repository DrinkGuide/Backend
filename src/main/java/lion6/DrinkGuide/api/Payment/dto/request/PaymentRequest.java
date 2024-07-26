package lion6.DrinkGuide.api.Payment.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentRequest {
    private String paymentKey;
    private String orderId;
    private int amount;
}