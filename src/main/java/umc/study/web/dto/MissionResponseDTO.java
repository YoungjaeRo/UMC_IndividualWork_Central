package umc.study.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddMissionResultDTO {
        private Long missionId; // 생성된 미션 ID
        private String name; // 생성된 미션 이름
        private LocalDateTime createdAt; // 생성 시간
    }
}
