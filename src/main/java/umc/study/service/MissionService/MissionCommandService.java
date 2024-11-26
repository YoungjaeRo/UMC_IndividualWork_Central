package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    Mission addMission(MissionRequestDTO.AddMissionDTO request);

    public MissionResponseDTO.CompletedMissionDTO completeMission(Long missionId);
}
