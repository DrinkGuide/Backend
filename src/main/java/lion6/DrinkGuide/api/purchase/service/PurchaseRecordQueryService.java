package lion6.DrinkGuide.api.purchase.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.nutrient.domain.Product;
import lion6.DrinkGuide.api.nutrient.repository.NutrientRepository;
import lion6.DrinkGuide.api.purchase.domain.ProductType;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.api.purchase.dto.response.PurchaseRecordCountResponseDto;
import lion6.DrinkGuide.api.purchase.dto.response.PurchaseRecordGetResponseDto;
import lion6.DrinkGuide.api.purchase.repository.PurchaseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PurchaseRecordQueryService {
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final MemberRepository memberRepository;
    private final NutrientRepository nutrientRepository;
    public List<PurchaseRecordGetResponseDto> getAllPurchaseRecords(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<PurchaseRecord> purchaseRecords = purchaseRecordRepository.findAllByMemberOrderByCreatedDate(member);
        return purchaseRecords.stream()
                .map(purchaseRecord -> PurchaseRecordGetResponseDto.of(purchaseRecord))
                .collect(Collectors.toList());
    }

    public List<String> getPurchaseCount(Long memberId) {
        Pageable pageable = PageRequest.of(0, 10); // Page 0, Size 10
        List<ProductType> productTypes = purchaseRecordRepository.findRecentProductTypesByMemberId(memberId, pageable);
        return productTypes.stream()
                .map(productType -> String.valueOf(productType))
                .collect(Collectors.toList());
    }

    public String getNutrientInfo(String productName) {
        Product product = nutrientRepository.findProductByProductNameOrThrow(productName);
        return product.getNutrientInfo();
    }
}
