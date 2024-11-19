package umc.study.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.missionService.MissionCommandService;
import umc.study.web.dto.mission.MissionRequestDTO;

@RestController
@RequestMapping("/missions")  //mission/add
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @Operation(summary = "특정 가게에 미션 추가")
    @PostMapping("/add")
    public ApiResponse<Long> addMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request) {
        Long missionId = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(missionId);
    }

    @Operation(summary = "가게의 미션을 도전 중인 미션에 추가")
    @PostMapping("/start")
    public ApiResponse<Long> startMission(@RequestBody @Valid MissionRequestDTO.StartMissionDTO request) {
            Long memberMissionId = missionCommandService.startMission(request);
            return ApiResponse.onSuccess(memberMissionId);
    }
}
