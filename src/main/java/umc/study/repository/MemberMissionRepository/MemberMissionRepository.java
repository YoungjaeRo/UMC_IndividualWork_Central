package umc.study.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMissionIdAndStatus(Long missionId, MissionStatus status);

    @Query("select (count(m) > 0) from MemberMission m where m.member = ?1 and m.mission.id = ?2 and m.status = ?3")
    boolean existsByMemberAndMissionIdAndStatus(Long memberId, Long mission_id, MissionStatus status);
}

