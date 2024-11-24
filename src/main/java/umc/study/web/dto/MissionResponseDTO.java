package umc.study.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddMissionResultDTO {
        private Long missionId; // 생성된 미션 ID
        private String name; // 생성된 미션 이름
        private LocalDateTime createdAt; // 생성 시간
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionListDTO {
        List<StoreMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionDTO {
        String missionName;
        String description;
        LocalDate startDate;
        LocalDate endDate;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyOngoingMissionListDTO {
        List<MyOngoingMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyOngoingMissionDTO {
        String missionName;
        String storeName;
        LocalDate startDate;
        LocalDate dueDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletedMissionDTO {
        private Long missionId;
        private String missionName;
        private String status;
    }
}
