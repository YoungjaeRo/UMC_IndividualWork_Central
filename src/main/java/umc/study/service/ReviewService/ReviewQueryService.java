package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;

import umc.study.domain.Review;

public interface ReviewQueryService {
	public Page<Review> getMyReviews(Integer page);
}
