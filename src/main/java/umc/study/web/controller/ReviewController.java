package umc.study.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "가게에 리뷰 추가",
        description = "가게에 대한 리뷰를 추가하는 API입니다. 리뷰를 추가하려면 가게 ID와 평가 내용을 입력해야 합니다.")
    @PostMapping("/")
    public ApiResponse<Long> addReview(@RequestBody @Valid ReviewRequestDTO.AddReviewDTO request) {
        Long reviewId = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(reviewId);
    }

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "현재 사용자가 작성한 리뷰 목록을 조회합니다. 페이징 처리가 포함되어 있으며, page 쿼리 파라미터를 사용합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다."),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON404", description = "사용자를 찾을 수 없습니다.")
    })
    @Parameters({
        @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviews(
        @RequestParam(name = "page") @Valid Integer page) {
        Page<Review> reviewPage = reviewQueryService.getMyReviews(page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewPage));
    }

}
