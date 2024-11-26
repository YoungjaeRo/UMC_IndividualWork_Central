package umc.study.converter;

import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MissionChallengeResultDTO toMissionChallengeResultDTO(
        MemberMission missionChallenge) {
        return MemberMissionResponseDTO.MissionChallengeResultDTO.builder()
            .missionId(missionChallenge.getMission().getId())
            .memberId(missionChallenge.getMember().getId())
            .challengedAt(missionChallenge.getChallengedAt())
            .status(missionChallenge.getStatus())
            .build();
    }
}
