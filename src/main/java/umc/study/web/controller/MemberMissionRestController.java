package umc.study.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberMissionConverter;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionCommandService;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member-missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(summary = "가게의 미션을 도전 중인 미션에 추가",
        description = "회원이 특정 가게의 미션에 도전하는 API입니다. 요청 시 미션 ID와 회원 정보를 입력해야 합니다.")
    @PostMapping("/start")
    public ApiResponse<MemberMissionResponseDTO.MissionChallengeResultDTO> addMissionChallenge(
            @RequestBody @Valid MemberMissionRequestDTO.AddMissionDTO request) {
        MemberMission missionChallenge = memberMissionCommandService.addMissionChallenge(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toMissionChallengeResultDTO(missionChallenge));
    }
}
