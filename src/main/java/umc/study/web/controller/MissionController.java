package umc.study.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.validation.annotation.ExistMemberMission;
import umc.study.validation.annotation.StoreExists;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;


    @Operation(summary = "가게에 미션 추가",
        description = "특정 가게에 새로운 미션을 추가하는 API입니다. 요청 시 미션 정보를 입력해야 합니다.")
    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에서 진행 가능한 미션 목록을 조회합니다. 페이징 처리 포함.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON404", description = "가게를 찾을 수 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getStoreMissions(
        @PathVariable @StoreExists Long storeId, @RequestParam @ValidPage Integer page) {
        Page<Mission> missionPage = missionQueryService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.toStoreMissionListDTO(missionPage));
    }

    @GetMapping("/my")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "현재 사용자가 진행 중인 미션 목록을 조회합니다. 페이징 처리가 포함됩니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다."),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON404", description = "사용자를 찾을 수 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.MyOngoingMissionListDTO> getMyOngoingMissions(
        @RequestParam(name = "page") @ValidPage Integer page) {
        Page<MemberMission> missionPage = missionQueryService.getMyOngoingMissions(page);
        return ApiResponse.onSuccess(MissionConverter.toMyOngoingMissionListDTO(missionPage));
    }

    @PatchMapping("/{missionId}/complete")
    @Operation(summary = "진행 중인 미션을 완료 상태로 변경하는 API", description = "특정 진행 중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다."),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON404", description = "미션을 찾을 수 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.CompletedMissionDTO> completeMission(@PathVariable @ExistMemberMission Long missionId) {
        MissionResponseDTO.CompletedMissionDTO response = missionCommandService.completeMission(missionId);
        return ApiResponse.onSuccess(response);
    }

}
