package lion6.DrinkGuide.api.payments.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="토스 페이먼츠 관련 컨트롤러",description = "토스 페이먼츠(테스트) 연동 시 쓰이는 컨트롤러입니다.")
public class PaymentsController {
    private final PaymentsCommandService paymentsCommandService;
    /**
     * orderId와 customerName기반으로 결제 대기 정보 저장
     */
    @PostMapping
    @Operation(summary = "토스 페이먼츠 결제 초기화",description = "토스 페이먼츠 결제 초기화를 진행합니다.(order_id 전달)")
    public ResponseEntity<ApiResponse<Object>> initializePayments(Principal principal, @RequestBody PaymentsInitializeRequestDto paymentInitializeRequestDto) {
        Long memberId = MemberUtil.getMemberId(principal);
        paymentsCommandService.initializePayments(memberId, paymentInitializeRequestDto);
        return ApiResponse.success(INITIALIZE_PAYMENTS_SUCCESS);
    }

    /**
     * 결제 승인 API
     */
    @PatchMapping("/approve")
    @Operation(summary = "토스 페이먼츠 결제 승인 처리 및 구독",description = "결제가 완료된 후 결제 승인 처리를 진행하며 구독 처리가 됩니다.")
    public ResponseEntity<ApiResponse<Object>> approvePayments(@RequestBody PaymentsApproveRequestDto paymentsApproveRequestDto) throws IOException, InterruptedException {
        paymentsCommandService.approvePayments(paymentsApproveRequestDto);
        return ApiResponse.success(PAYMENTS_APPROVAL_SUCCESS);
    }
}