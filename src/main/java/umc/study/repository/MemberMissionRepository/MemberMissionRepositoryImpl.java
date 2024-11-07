package umc.study.repository.MemberMissionRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import umc.study.domain.QMission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.QMemberMission;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;

    @Override
    public Page<MemberMission> findMissionsByStatus(Long memberId, MissionStatus status, Pageable pageable) {
        List<MemberMission> results = jpaQueryFactory
            .selectFrom(memberMission)
            .leftJoin(memberMission.mission, mission).fetchJoin()
            .where(memberMission.member.id.eq(memberId)
                .and(memberMission.status.eq(status)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = jpaQueryFactory
            .selectFrom(memberMission)
            .where(memberMission.member.id.eq(memberId)
                .and(memberMission.status.eq(status)))
            .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}