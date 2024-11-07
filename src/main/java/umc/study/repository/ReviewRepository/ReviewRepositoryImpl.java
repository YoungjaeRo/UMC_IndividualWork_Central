package umc.study.repository.ReviewRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import umc.study.domain.Member;
import umc.study.domain.QReview;
import umc.study.domain.Review;
import umc.study.domain.Store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;
    private final EntityManager entityManager;

    @Override
    public void createReview(Long memberId, Long storeId, String title, Float score, String body) {
        Review newReview = Review.builder()
            .title(title)
            .score(score)
            .body(body)
            .member(Member.builder().id(memberId).build())
            .store(Store.builder().id(storeId).build())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        entityManager.persist(newReview);
    }
}