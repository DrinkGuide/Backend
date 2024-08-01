package lion6.DrinkGuide.api.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="멤버 관련 컨트롤러",description = "멤버와 관련된 컨트롤러입니다.")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/reissue")
    @Operation(summary = "엑세스 토큰 재발급",description = "엑세스 토큰 만료 시 리프레쉬 토큰을 통한 엑세스 토큰 재발급합니다.")
    public ResponseEntity<ApiResponse<TokenReissueResponseDto>> reissueAccessToken(@Valid @RequestBody TokenReissueRequestDto tokenReissueRequestDto) {
        TokenReissueResponseDto newAccessToken = memberCommandService.reissueAccessToken(tokenReissueRequestDto.refreshToken());
        return ApiResponse.success(GET_NEW_TOKEN_SUCCESS, newAccessToken);
    }
    @GetMapping("/subscribe")
    @Operation(summary = "닉네임 및 구독 정보 조회",description = "<my page> 닉네임 및 구독여부를 조회합니다. 엑세스 토큰이 필요합니다.")
    public ResponseEntity<ApiResponse<MemberSubscribeResponseDto>> getMemberInfo(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        return ApiResponse.success(GET_IS_SUBSCRIBE_SUCCESS, memberQueryService.getSubscribeType(memberId));
    }

    @PatchMapping("/ui-type")
    @Operation(summary = "UI 타입 변환",description = "어둡게/밝게 UI 타입을 변환합니다. 엑세스 토큰이 필요합니다.")
    public ResponseEntity<ApiResponse<Object>> updateUiType(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        memberCommandService.updateUiType(memberId);
        return ApiResponse.success(UPDATE_UI_TYPE_SUCCESS);
    }

}
