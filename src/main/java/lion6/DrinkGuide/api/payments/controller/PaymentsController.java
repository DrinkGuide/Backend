package lion6.DrinkGuide.api.payments.controller;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsApproveRequestDto;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsInitializeRequestDto;
import lion6.DrinkGuide.api.payments.service.PaymentsCommandService;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;

import static lion6.DrinkGuide.common.response.SuccessStatus.INITIALIZE_PAYMENTS_SUCCESS;
import static lion6.DrinkGuide.common.response.SuccessStatus.PAYMENTS_APPROVAL_SUCCESS;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentsCommandService paymentsCommandService;
    /**
     * orderId와 customerName기반으로 결제 대기 정보 저장
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> initializePayments(Principal principal, @RequestBody PaymentsInitializeRequestDto paymentInitializeRequestDto) {
        Long memberId = MemberUtil.getMemberId(principal);// 또는 필요한 다른 속성을 사용
        paymentsCommandService.initializePayments(memberId, paymentInitializeRequestDto);
        return ApiResponse.success(INITIALIZE_PAYMENTS_SUCCESS);
    }

    /**
     * 결제 승인 API
     */
    @PatchMapping("/approve")
    public ResponseEntity<ApiResponse<Object>> approvePayments(@RequestBody PaymentsApproveRequestDto paymentsApproveRequestDto) throws IOException, InterruptedException {
        System.out.println("-------------------------컨트롤러 실행됨-------------------------");
        paymentsCommandService.approvePayments(paymentsApproveRequestDto);
        return ApiResponse.success(PAYMENTS_APPROVAL_SUCCESS);
    }
}


//OkHttpClient client = new OkHttpClient();
//
//okhttp3.RequestBody body = okhttp3.RequestBody.create(
//        MediaType.parse("application/json"),
//        "{\"orderId\":\"" + paymentRequest.getOrderId() + "\",\"amount\":" + paymentRequest.getAmount() + ",\"paymentKey\":\"" + paymentRequest.getPaymentKey() + "\"}"
//);
//String encryptedSecretKey = "Basic " + java.util.Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes());
//
//Request request = new Request.Builder()
//        .url("https://api.tosspayments.com/v1/payments/confirm")
//        .post(body)
//        .addHeader("Authorization", encryptedSecretKey)
//        .addHeader("Content-Type", "application/json")
//        .build();
//
//        try (Response response = client.newCall(request).execute()) {
//        if (response.isSuccessful()) {
//        System.out.println("response = " + response);
//                model.addAttribute("message", "결제가 완료되었습니다.");
//                return "success";
//                        } else {
//                        model.addAttribute("message", "결제에 실패했습니다.");
//                return "fail";
//                        }
//                        } catch (IOException e) {
//        e.printStackTrace();
//            model.addAttribute("message", "오류가 발생했습니다.");
//            return "fail";
//                    }