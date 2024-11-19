package umc.study.service.reviewService;

import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Long addReview(ReviewRequestDTO.CreateReviewDTO request);
}
