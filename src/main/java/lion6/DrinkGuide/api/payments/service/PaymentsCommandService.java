package lion6.DrinkGuide.api.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lion6.DrinkGuide.api.payments.domain.PaymentsHistory;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsApproveRequestDto;
import lion6.DrinkGuide.api.payments.dto.request.PaymentsInitializeRequestDto;
import lion6.DrinkGuide.api.payments.dto.response.PaymentsApproveApiResponseDto;
import lion6.DrinkGuide.api.payments.dto.response.PaymentsApproveResponseDto;
import lion6.DrinkGuide.api.payments.repository.PaymentsRepostory;
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
    private final PaymentsRepostory paymentsRepostory;
    @Value("${payments.toss.test-secret-key}")
    private String secretKey;

    public void initializePayments(Long memberId, PaymentsInitializeRequestDto paymentsInitializeRequestDto) {
        System.out.println("memberId = " + memberId);
        Member member = memberRepository.findMemberByIdOrThrow(memberId);

        PaymentsHistory paymentsHistory = PaymentsHistory.builder()
                .member(member)
                .orderId(paymentsInitializeRequestDto.orderId())
                .build();
        paymentsRepostory.save(paymentsHistory);
    }

    public void approvePayments(PaymentsApproveRequestDto paymentsApproveRequestDto) throws IOException, InterruptedException {
        System.out.println("-------------------------서비스 실행됨-------------------------");

        String beforeEncoding = secretKey + ":";
        String afterEnconding = Base64.getEncoder().encodeToString(beforeEncoding.getBytes(StandardCharsets.UTF_8));
        System.out.println("afterEnconding = " + afterEnconding);
        // JSON 요청 본문을 생성
        String requestBody = String.format(
                "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
                paymentsApproveRequestDto.paymentKey(),
                paymentsApproveRequestDto.orderId(),
                paymentsApproveRequestDto.amount()
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
                .header("Authorization", "Basic " + afterEnconding)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        System.out.println("-------------------------API 요청 실행됨-------------------------");
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        PaymentsApproveApiResponseDto paymentsApproveApiResponseDto = objectMapper.readValue(response.body(), PaymentsApproveApiResponseDto.class);
        PaymentsHistory paymentsHistory = paymentsRepostory.findPaymentsHistoryByOrderIdOrThrow(paymentsApproveApiResponseDto.orderId());
        paymentsHistory.approvePaymentsHistory(paymentsApproveApiResponseDto.paymentKey(), paymentsApproveRequestDto.amount());
        paymentsHistory.getMember().subscribe();
    }
}
