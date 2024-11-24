package umc.study.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import umc.study.domain.enums.MissionStatus;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionChallengeResultDTO {
        private Long missionId;   // 미션 ID
        private Long memberId;    // 회원 ID
        private LocalDateTime challengedAt;  // 도전 시작 시간
        private MissionStatus status;  // 미션 상태
    }
}
