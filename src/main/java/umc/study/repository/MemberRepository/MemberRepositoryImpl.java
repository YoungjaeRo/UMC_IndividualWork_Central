package umc.study.repository.MemberRepository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import umc.study.domain.Member;
import umc.study.domain.QMember;
import umc.study.domain.QReview;
import umc.study.domain.mapping.QMemberMission;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;
    private final QReview review = QReview.review;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public Member findMemberProfile(Long memberId) {
        return jpaQueryFactory
            .selectFrom(member)
            .leftJoin(member.reviewList, review).fetchJoin()
            .leftJoin(member.memberMissionList, memberMission).fetchJoin()
            .where(member.id.eq(memberId))
            .fetchOne();
    }
}