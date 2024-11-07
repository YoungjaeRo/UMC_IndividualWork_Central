package umc.study.repository.MissionRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import umc.study.domain.Mission;
import umc.study.domain.QMission;
import umc.study.domain.QStore;
import umc.study.domain.enums.MissionStatus;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public Page<Mission> findAvailableMissionsByRegion(Long regionId, Pageable pageable) {
        List<Mission> results = jpaQueryFactory
            .selectFrom(mission)
            .leftJoin(mission.store, store).fetchJoin()
            .where(store.region.id.eq(regionId)
                .and(mission.status.eq(MissionStatus.CHALLENGING)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = jpaQueryFactory
            .selectFrom(mission)
            .where(store.region.id.eq(regionId)
                .and(mission.status.eq(MissionStatus.CHALLENGING)))
            .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}