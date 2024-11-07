package umc.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepositoryCustom {
    Page<MemberMission> findMissionsByStatus(Long memberId, MissionStatus status, Pageable pageable);
}