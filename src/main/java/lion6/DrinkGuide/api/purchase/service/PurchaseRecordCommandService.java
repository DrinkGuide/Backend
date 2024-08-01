package lion6.DrinkGuide.api.purchase.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.api.purchase.dto.request.PurchaseRecordCreateRequestDto;
import lion6.DrinkGuide.api.purchase.repository.PurchaseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseRecordCommandService {
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final MemberRepository memberRepository;
    public void savePurchaseRecord(Long memberId, PurchaseRecordCreateRequestDto purchaseRecordCreateRequestDto) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        PurchaseRecord purchaseRecord = PurchaseRecord.builder()
                .member(member)
                .productName(purchaseRecordCreateRequestDto.productName())
                .productType(purchaseRecordCreateRequestDto.productType())
                .build();
        purchaseRecordRepository.save(purchaseRecord);
    }
}
