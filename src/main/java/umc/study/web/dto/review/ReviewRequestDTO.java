package umc.study.web.dto.review;

import lombok.Getter;

public class ReviewRequestDTO {

	@Getter
	public class CreateReviewDTO {
		private Long storeId;
		private String title;
		private Float score;
	}
}
