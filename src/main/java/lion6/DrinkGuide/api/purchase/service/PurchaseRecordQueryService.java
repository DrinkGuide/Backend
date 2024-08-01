package lion6.DrinkGuide.api.purchase.service;

import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.purchase.domain.ProductType;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.api.purchase.dto.response.PurchaseRecordCountResponseDto;
import lion6.DrinkGuide.api.purchase.dto.response.PurchaseRecordGetResponseDto;
import lion6.DrinkGuide.api.purchase.repository.PurchaseRecordRepository;
import lombok.RequiredArgsConstructor;
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
    public List<PurchaseRecordGetResponseDto> getAllPurchaseRecords(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<PurchaseRecord> purchaseRecords = purchaseRecordRepository.findAllByMemberOrderByCreatedDate(member);
        return purchaseRecords.stream()
                .map(purchaseRecord -> PurchaseRecordGetResponseDto.of(purchaseRecord))
                .collect(Collectors.toList());
    }

    public List<PurchaseRecordCountResponseDto> getPurchaseCount(Long memberId) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        List<Object[]> results = purchaseRecordRepository.countByProductTypeAndMember(member);
        List<PurchaseRecordCountResponseDto> purchaseRecordCountResponseDtos = new ArrayList<>();
        for (Object[] result : results) {
            purchaseRecordCountResponseDtos.add(new PurchaseRecordCountResponseDto((String) result[0], (Long) result[1]));
        }
        return purchaseRecordCountResponseDtos;
    }
}