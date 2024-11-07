package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.study.domain.Mission;

public interface MissionRepositoryCustom {
    Page<Mission> findAvailableMissionsByRegion(Long regionId, Pageable pageable);
}