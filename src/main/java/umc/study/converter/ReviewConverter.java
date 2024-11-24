package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.AddReviewDTO request, Store store) {
        return Review.builder()
            .store(store)
            .content(request.getContent())
            .rating(request.getRating())
            .build();
    }
}
