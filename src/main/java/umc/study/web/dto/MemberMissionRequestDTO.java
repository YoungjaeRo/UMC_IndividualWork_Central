package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistMission;

public class MemberMissionRequestDTO {

    @Getter
    public static class AddMissionDTO {
        @NotNull
        private Long memberId;  // 도전하려는 회원 ID

        @NotNull
        @ExistMission
        private Long missionId;  // 도전하려는 미션 ID
    }
}
