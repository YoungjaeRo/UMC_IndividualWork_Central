package umc.study.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.reviewService.ReviewCommandService;
import umc.study.web.dto.review.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @Operation(summary = "특정 가게에 리뷰 추가")
    @PostMapping("/add")
    public ApiResponse<Long> addReview(@RequestBody @Valid ReviewRequestDTO.CreateReviewDTO request) {
        Long reviewId = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(reviewId);
    }
}
