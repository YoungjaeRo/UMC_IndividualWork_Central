package umc.study.web.dto.mission;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MissionRequestDTO {

	@Getter
	public class StartMissionDTO {
		private Long missionId;

		private Long memberId;

	}

	@Getter
	public class CreateMissionDTO {
		private Long storeId;

		private String missionSpec;

		private LocalDate deadline;

	}
}
