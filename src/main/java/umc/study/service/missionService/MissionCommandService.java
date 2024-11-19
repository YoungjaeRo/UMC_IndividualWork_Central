package umc.study.service.missionService;

import umc.study.domain.Mission;
import umc.study.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {
    Long addMission(MissionRequestDTO.CreateMissionDTO request);
    Long startMission(MissionRequestDTO.StartMissionDTO request);
}
