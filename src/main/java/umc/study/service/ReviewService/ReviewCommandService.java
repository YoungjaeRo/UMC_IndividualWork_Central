package umc.study.service.ReviewService;

import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
	public Long addReview(ReviewRequestDTO.AddReviewDTO request);
}
