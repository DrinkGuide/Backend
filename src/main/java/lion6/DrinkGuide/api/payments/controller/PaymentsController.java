package lion6.DrinkGuide.api.payments.controller;
import lion6.DrinkGuide.api.payments.dto.request.PaymentRequest;
import lion6.DrinkGuide.common.util.MemberUtil;
import okhttp3.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/api/v1/payment")
public class PaymentsController {

    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    /**
     * 결제 위젯 렌더링 Thymleaf
     */
    @GetMapping("/")
    public String checkout(Principal principal, Model model) {
        Long memberId = MemberUtil.getMemberId(principal);// 또는 필요한 다른 속성을 사용
        model.addAttribute("memberId", memberId);
        return "checkout";
    }

    /**
     * 결제 승인 API
     */
    @PostMapping("/confirm")
    public String confirm(@org.springframework.web.bind.annotation.RequestBody PaymentRequest paymentRequest, Model model) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.RequestBody body = okhttp3.RequestBody.create(
                MediaType.parse("application/json"),
                "{\"orderId\":\"" + paymentRequest.getOrderId() + "\",\"amount\":" + paymentRequest.getAmount() + ",\"paymentKey\":\"" + paymentRequest.getPaymentKey() + "\"}"
        );
        String encryptedSecretKey = "Basic " + java.util.Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes());

        Request request = new Request.Builder()
                .url("https://api.tosspayments.com/v1/payments/confirm")
                .post(body)
                .addHeader("Authorization", encryptedSecretKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("response = " + response);
                model.addAttribute("message", "결제가 완료되었습니다.");
                return "success";
            } else {
                model.addAttribute("message", "결제에 실패했습니다.");
                return "fail";
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "오류가 발생했습니다.");
            return "fail";
        }
    }

    /**
     * 성공 시 보낼 Thymleaf 페이지
     */
    @GetMapping("/success")
    public String success() {
        return "success";
    }

    /**
     * 실패 시 보낼 Thymleaf 페이지
     */
    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }
}
