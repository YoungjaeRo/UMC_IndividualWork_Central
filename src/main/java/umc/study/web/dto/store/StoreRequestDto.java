package umc.study.web.dto.store;

import lombok.Getter;

public class StoreRequestDto {

	@Getter
	public class CreateStoreDTO {

		private String name;

		private String address;

		private Long regionId;

		private Float score;
	}
}
