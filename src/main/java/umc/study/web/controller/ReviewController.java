package umc.study.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @Operation(summary = "가게에 리뷰 추가",
        description = "가게에 대한 리뷰를 추가하는 API입니다. 리뷰를 추가하려면 가게 ID와 평가 내용을 입력해야 합니다.")
    @PostMapping("/")
    public ApiResponse<Long> addReview(@RequestBody @Valid ReviewRequestDTO.AddReviewDTO request) {
        Long reviewId = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(reviewId);
    }
}
