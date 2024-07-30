package lion6.DrinkGuide.api.payments.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentsApproveResponseDto(
        String mId,
        String lastTransactionKey,
        String paymentKey,
        String orderId,
        String orderName,
        int taxExemptionAmount,
        String status,
        LocalDateTime requestedAt,
        LocalDateTime approvedAt,
        boolean useEscrow,
        boolean cultureExpense,
        Object card,
        Object virtualAccount, // Object로 처리
        Object transfer, // Object로 처리
        String mobilePhone,
        String giftCertificate,
        String cashReceipt,
        Object cashReceipts, // Object로 처리
        Object discount, // Object로 처리
        Object cancels, // Object로 처리
        String secret,
        String type,
        EasyPayDto easyPay,
        String country,
        Object failure,
        boolean isPartialCancelable,
        ReceiptDto receipt,
        CheckoutDto checkout,
        String currency,
        int totalAmount,
        int balanceAmount,
        int suppliedAmount,
        int vat,
        int taxFreeAmount,
        String method,
        String version
) {}
