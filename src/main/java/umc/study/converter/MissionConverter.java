package umc.study.converter;

import java.time.LocalDateTime;

import umc.study.domain.Mission;
import umc.study.domain.Store;
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
}
