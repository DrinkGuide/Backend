package lion6.DrinkGuide.api.purchase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.api.purchase.service.PurchaseRecordCommandService;
import lion6.DrinkGuide.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@Tag(name="구매 데이터 수집 컨트롤러",description = "스캔 기능을 통해 구매한 상품을 기록하고 관리하는 컨트롤러입니다.")
public class PurchaseRecordController {
    private final PurchaseRecordCommandService purchaseRecordCommandService;
    /**
     * 구매 내역 저장
     */
//    @PostMapping
//    @Operation(summary = "구매 내역 저장", description = "구매한 상품 기록을 저장합니다.")
//    public ResponseEntity<ApiResponse<Object>> createPurchaseRecord(
//            Principal principal,
//            @RequestBody PurchaseRecordCreateRequestDto purchaseRecordCreateRequestDto
//    ) {
//
//        return ApiResponse.success();
//    }


    /**
     * 구매 내역 조회
     */
}
