package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        return missionRepository.findAllByStoreId(storeId, PageRequest.of(page, 10));
    }
    @Override
    public Page<MemberMission> getMyOngoingMissions(Integer page) {
        // 현재 하드코딩된 사용자 ID를 사용합니다.
        Long memberId = 1L;
        return memberMissionRepository.findAllByMemberIdAndStatus(
            memberId, MissionStatus.IN_PROGRESS, PageRequest.of(page, 10));
    }
}
