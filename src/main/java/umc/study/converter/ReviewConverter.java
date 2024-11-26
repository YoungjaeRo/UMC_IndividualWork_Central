package umc.study.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.AddReviewDTO request, Store store) {
        return Review.builder()
            .store(store)
            .content(request.getContent())
            .rating(request.getRating())
            .build();
    }

    public static ReviewResponseDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResponseDTO.MyReviewDTO.builder()
            .storeName(review.getStore().getName())
            .score(review.getScore())
            .body(review.getBody())
            .createdAt(review.getCreatedAt().toLocalDate())
            .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {
        List<ReviewResponseDTO.MyReviewDTO> reviews = reviewPage.stream()
            .map(ReviewConverter::toMyReviewDTO)
            .collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewListDTO.builder()
            .reviewList(reviews)
            .listSize(reviews.size())
            .totalPage(reviewPage.getTotalPages())
            .totalElements(reviewPage.getTotalElements())
            .isFirst(reviewPage.isFirst())
            .isLast(reviewPage.isLast())
            .build();
    }
}
