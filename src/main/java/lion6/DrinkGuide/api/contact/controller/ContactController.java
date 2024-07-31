package lion6.DrinkGuide.api.contact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lion6.DrinkGuide.api.contact.dto.request.ContactCreateRequestDto;
import lion6.DrinkGuide.api.contact.dto.response.ContactGetAllResponseDto;
import lion6.DrinkGuide.api.contact.service.ContactCommandService;
import lion6.DrinkGuide.api.contact.service.ContactQueryService;
import lion6.DrinkGuide.api.member.service.MemberQueryService;
import lion6.DrinkGuide.common.oauth.CustomOAuth2User;
import lion6.DrinkGuide.common.response.ApiResponse;
import lion6.DrinkGuide.common.response.SuccessStatus;
import lion6.DrinkGuide.common.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static lion6.DrinkGuide.common.response.SuccessStatus.CREATE_CONTACT_SUCCESS;
import static lion6.DrinkGuide.common.response.SuccessStatus.GET_CONTACTS_SUCCESS;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
@Tag(name="문의(피드백) 관련 컨트롤러",description = "문의하기(피드백) 관련 컨트롤러입니다.")
public class ContactController {
    private final ContactCommandService contactCommandService;
    private final ContactQueryService contactQueryService;

    /**
     * 문의 등록
     */
    @PostMapping
    @Operation(summary = "문의 사항 등록",description = "문의 사항을 등록합니다.")
    public ResponseEntity<ApiResponse<Object>> createContact(@Valid @RequestBody ContactCreateRequestDto contactCreateRequestDto, Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        contactCommandService.createContact(memberId, contactCreateRequestDto.title(), contactCreateRequestDto.content());
        return ApiResponse.success(CREATE_CONTACT_SUCCESS);
    }

    /**
     * Admin 페이지 서 문의 조회
     * ResponseEntity<ApiResponse<List<ContactGetAllResponseDto>>>
     */
    @GetMapping
    @Operation(summary = "문의 사항 조회",description = "문의 사항들을 조회합니다. ADMIN만 접근 가능합니다.")
    public ResponseEntity<ApiResponse<List<ContactGetAllResponseDto>>> getContacts(Principal principal) {
        Long memberId = MemberUtil.getMemberId(principal);
        return ApiResponse.success(GET_CONTACTS_SUCCESS,contactQueryService.getContacts(memberId));

    }
}
