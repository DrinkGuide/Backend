package lion6.DrinkGuide.api.purchase.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.api.purchase.dto.request.PurchaseRecordCreateRequestDto;
import lion6.DrinkGuide.api.purchase.dto.request.PurchaseRecordGetResponseDto;
import lion6.DrinkGuide.api.purchase.repository.PurchaseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseRecordCommandService {
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final MemberRepository memberRepository; // 멤버 조회를 위한 참조

    // 구매하면 저장하기
    public void savePurchaseRecord(Long memberId, PurchaseRecordCreateRequestDto purchaseRecordCreateRequestDto) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        PurchaseRecord purchaseRecord = PurchaseRecord.builder()
                .member(member)
                .productName(purchaseRecordCreateRequestDto.productName())
                .build();
        purchaseRecordRepository.save(purchaseRecord);
    }

    // 조회하기 (전체 구매 내역)
    // Optinonal로 해도 되나?
    public List<PurchaseRecordGetResponseDto> getAllPurchaseRecords(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<PurchaseRecord> purchaseRecords = purchaseRecordRepository.findAllByMemberOrderByCreatedDate(member);
        return purchaseRecords.stream()
                .map(purchaseRecord -> PurchaseRecordGetResponseDto.of(purchaseRecord))
                .collect(Collectors.toList());
    }

}
