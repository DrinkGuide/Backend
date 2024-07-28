package lion6.DrinkGuide.api.member.controller;

import jakarta.validation.Valid;
import lion6.DrinkGuide.api.member.dto.request.TokenReissueRequestDto;
import lion6.DrinkGuide.api.member.dto.response.MemberSubscribeResponseDto;
import lion6.DrinkGuide.api.member.dto.response.TokenReissueResponseDto;
import lion6.DrinkGuide.api.member.service.MemberCommandService;
import lion6.DrinkGuide.api.member.service.MemberQueryService;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static lion6.DrinkGuide.common.response.SuccessStatus.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    /**
     * 엑세스 토큰 만료 시 재발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<TokenReissueResponseDto>> reissueAccessToken(@Valid @RequestBody TokenReissueRequestDto tokenReissueRequestDto) {
        TokenReissueResponseDto newAccessToken = memberCommandService.reissueAccessToken(tokenReissueRequestDto.refreshToken());
        return ApiResponse.success(GET_NEW_TOKEN_SUCCESS, newAccessToken);
    }

    /**
     * 구독 여부 체크
     */
    @GetMapping("/subscribe")
    public ResponseEntity<ApiResponse<MemberSubscribeResponseDto>> getIsSubscribe(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        return ApiResponse.success(GET_IS_SUBSCRIBE_SUCCESS, memberQueryService.getIsSubscribe(memberId));
    }

    /**
     * UI Type 변경
     */
    @PatchMapping("/ui-type")
    public ResponseEntity<ApiResponse<Object>> updateUiType(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        memberCommandService.updateUiType(memberId);
        return ApiResponse.success(UPDATE_UI_TYPE_SUCCESS);
    }

}
