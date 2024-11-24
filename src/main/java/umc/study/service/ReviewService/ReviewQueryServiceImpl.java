package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import umc.study.domain.Review;
import umc.study.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Integer page) {
        // 현재는 하드코딩된 사용자 ID를 사용합니다.
        Long memberId = 1L;
        return reviewRepository.findAllByMemberId(memberId, PageRequest.of(page, 10));
    }
}