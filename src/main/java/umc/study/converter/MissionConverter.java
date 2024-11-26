package umc.study.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.AddMissionDTO request, Store store) {
        return Mission.builder()
            .store(store)
            .name(request.getName())
            .description(request.getDescription())
            .point(request.getPoint())
            .build();
    }

    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission) {
        return MissionResponseDTO.AddMissionResultDTO.builder()
            .missionId(mission.getId())
            .name(mission.getName())
            .createdAt(mission.getCreatedAt())
            .build();
    }

    public static MissionResponseDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResponseDTO.StoreMissionDTO.builder()
            .missionName(mission.getName())
            .description(mission.getDescription())
            .startDate(mission.getStartDate())
            .endDate(mission.getEndDate())
            .build();
    }

    public static MissionResponseDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.StoreMissionDTO> missions = missionPage.stream()
            .map(MissionConverter::toStoreMissionDTO)
            .collect(Collectors.toList());

        return MissionResponseDTO.StoreMissionListDTO.builder()
            .missionList(missions)
            .listSize(missions.size())
            .totalPage(missionPage.getTotalPages())
            .totalElements(missionPage.getTotalElements())
            .isFirst(missionPage.isFirst())
            .isLast(missionPage.isLast())
            .build();
    }

    public static MissionResponseDTO.MyOngoingMissionDTO toMyOngoingMissionDTO(MemberMission memberMission) {
        return MissionResponseDTO.MyOngoingMissionDTO.builder()
            .missionName(memberMission.getMission().getName())
            .storeName(memberMission.getMission().getStore().getName())
            .startDate(memberMission.getStartDate())
            .dueDate(memberMission.getDueDate())
            .build();
    }

    public static MissionResponseDTO.MyOngoingMissionListDTO toMyOngoingMissionListDTO(Page<MemberMission> missionPage) {
        List<MissionResponseDTO.MyOngoingMissionDTO> missions = missionPage.stream()
            .map(MissionConverter::toMyOngoingMissionDTO)
            .collect(Collectors.toList());

        return MissionResponseDTO.MyOngoingMissionListDTO.builder()
            .missionList(missions)
            .listSize(missions.size())
            .totalPage(missionPage.getTotalPages())
            .totalElements(missionPage.getTotalElements())
            .isFirst(missionPage.isFirst())
            .isLast(missionPage.isLast())
            .build();
    }
}
