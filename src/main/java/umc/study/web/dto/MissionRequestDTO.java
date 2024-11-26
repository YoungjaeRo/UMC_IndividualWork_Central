package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    @Builder
    public static class AddMissionDTO {
        @NotNull
        private Long storeId; // 미션이 추가될 가게 ID

        @NotBlank
        private String name; // 미션 이름

        @NotBlank
        private String description; // 미션 설명

        @NotNull
        private Integer point; // 미션 완료 시 제공될 포인트
    }
}
