package lion6.DrinkGuide.api.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.domain.SubscribeType;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.payments.domain.PaymentsHistory;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsApproveRequestDto;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsInitializeRequestDto;
import lion6.DrinkGuide.api.payments.dto.response.PaymentsApproveApiResponseDto;
import lion6.DrinkGuide.api.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentsCommandService {
    private final MemberRepository memberRepository;
    private final PaymentsRepository paymentsRepostory;
    @Value("${payments.toss.test-secret-key}")
    private String secretKey;

    public void initializePayments(Long memberId, PaymentsInitializeRequestDto paymentsInitializeRequestDto) {
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        PaymentsHistory paymentsHistory = PaymentsHistory.builder()
                .member(member)
                .orderId(paymentsInitializeRequestDto.orderId())
                .amount(paymentsInitializeRequestDto.amount())
                .subscribeType(paymentsInitializeRequestDto.subscribeType())
                .build();
        paymentsRepostory.save(paymentsHistory);
    }

    public void approvePayments(PaymentsApproveRequestDto paymentsApproveRequestDto) throws IOException, InterruptedException {
        String beforeEncoding = secretKey + ":";
        String afterEnconding = Base64.getEncoder().encodeToString(beforeEncoding.getBytes(StandardCharsets.UTF_8));

        String orderId = paymentsApproveRequestDto.orderId();
        int amount = paymentsApproveRequestDto.amount();
        System.out.println("1차");
        System.out.println("orderId = " + orderId);
        System.out.println("amount = " + amount);
        paymentsRepostory.findPaymentsHistoryByOrderIdAndAmountOrThrow(orderId, amount);
        System.out.println("2차");
        // JSON 요청 본문을 생성
        String requestBody = String.format(
                "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
                paymentsApproveRequestDto.paymentKey(),
                orderId,
                amount
        );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
                .header("Authorization", "Basic " + afterEnconding)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        PaymentsApproveApiResponseDto paymentsApproveApiResponseDto = objectMapper.readValue(response.body(), PaymentsApproveApiResponseDto.class);
        PaymentsHistory paymentsHistory = paymentsRepostory.findPaymentsHistoryByOrderIdOrThrow(paymentsApproveApiResponseDto.orderId());
        paymentsHistory.approvePaymentsHistory(paymentsApproveApiResponseDto.paymentKey());

        SubscribeType subscribeType = paymentsHistory.getSubscribeType();
        paymentsHistory.getMember().subscribe(subscribeType);
    }
}
