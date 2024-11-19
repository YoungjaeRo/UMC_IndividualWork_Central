package umc.study.service.reviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional

    @Override
    public Long addReview(ReviewRequestDTO.CreateReviewDTO request) {
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        //로그인이 구현되어 있지 않으므로 하드코딩
        Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        // 리뷰 생성하기
        Review review = Review.builder()
            .title(request.getTitle())
            .store(store)
            .score(request.getScore())
            .member(member)
            .build();

        reviewRepository.save(review);
        return review.getId();
    }
}
