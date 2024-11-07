package umc.study.repository.ReviewRepository;

public interface ReviewRepositoryCustom {
    void createReview(Long memberId, Long storeId, String title, Float score, String body);
}