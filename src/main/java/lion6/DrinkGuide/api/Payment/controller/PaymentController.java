package lion6.DrinkGuide.api.Payment.controller;
import lion6.DrinkGuide.api.Payment.dto.request.PaymentRequest;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    @GetMapping("/")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

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
}
