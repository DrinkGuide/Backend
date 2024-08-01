package lion6.DrinkGuide.api.purchase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lion6.DrinkGuide.api.purchase.dto.request.PurchaseRecordCreateRequestDto;
import lion6.DrinkGuide.api.purchase.dto.request.PurchaseRecordGetResponseDto;
import lion6.DrinkGuide.api.purchase.service.PurchaseRecordCommandService;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static lion6.DrinkGuide.common.response.SuccessStatus.*;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
@Tag(name="구매 데이터 수집 컨트롤러",description = "스캔 기능을 통해 구매한 상품을 기록하고 관리하는 컨트롤러입니다.")
public class PurchaseRecordController {
    private final PurchaseRecordCommandService purchaseRecordCommandService;

    // 구매 기록 저장
    @PostMapping // URL 결정
    @Operation(summary = "구매 기록 저장", description = "새로운 구매 기록을 저장합니다.")
    public ResponseEntity<ApiResponse<Object>> savePurchaseRecord(
            @RequestBody PurchaseRecordCreateRequestDto purchaseRecordCreateRequestDto,
            Principal principal
    ) {
        Long memberId = MemberUtil.getMemberId(principal);
        purchaseRecordCommandService.savePurchaseRecord(memberId, purchaseRecordCreateRequestDto);
        return ApiResponse.success(CREATE_PURCHASE_SUCCESS);
    }

    // 전체 구매 내역 조회
    @GetMapping // URL 결정
    @Operation(summary = "전체 구매 내역 조회", description = "전체 구매 기록을 조회합니다.")
    public ResponseEntity<ApiResponse<List<PurchaseRecordGetResponseDto>>> getAllPurchaseRecords(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        return ApiResponse.success(GET_PURCHASES_SUCCESS, purchaseRecordCommandService.getAllPurchaseRecords(memberId));
    }

}
