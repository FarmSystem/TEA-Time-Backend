package farm.teatimeapi.controller;

import farm.teatimeapi.dto.community.response.FindMemberDto;
import farm.teatimeapi.dto.community.response.GetCommunityDto;
import farm.teatimeapi.service.CommunityService;
import farm.teatimecore.annotation.UserId;
import farm.teatimecore.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
@Tag(name = "Community", description = "커뮤니티 관련 API")
public class CommunityController {

    private final CommunityService communityService;

    @Operation(summary = "커뮤니티 다이어리 불러오기", description = "다른 유저의 다이어리 목록을 불러옵니다.")
    @GetMapping("")
    public ResponseDto<GetCommunityDto> getCommunity(
            @UserId Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseDto.ok(communityService.getCommunity(userId, page, size));
    }

    @Deprecated
    @Operation(summary = "회원 찾기 페이지", description = "회원 찾기 페이지를 불러옵니다. 랜덤으로 추천친구를 불러옵니다.")
    @GetMapping("/member")
    public ResponseDto<FindMemberDto> getMember(
            @UserId Long userId
    ) {
        return ResponseDto.ok(communityService.findMember(userId));
    }

    @Deprecated
    @Operation(summary = "회원 검색 기능", description = "회원을 검색합니다.")
    @PostMapping("/member")
    public ResponseDto<FindMemberDto> searchMember(
            @RequestParam("nickname") String nickname
    ) {
        return ResponseDto.ok(communityService.searchMember(nickname));
    }

    @Deprecated
    @Operation(summary = "회원 프로필 페이지", description = "선택한 회원의 프로필 페이지를 불러옵니다.")
    @GetMapping("/member/{memberId}")
    public ResponseDto<?> getMemberProfile(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseDto.ok(communityService.getMemberProfile(memberId, page, size));
    }
}
