package umc.study.service.ReviewService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Long addReview(ReviewRequestDTO.AddReviewDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        
        Review review = ReviewConverter.toReview(request, store);
        reviewRepository.save(review);
        return review.getId();
    }
}
