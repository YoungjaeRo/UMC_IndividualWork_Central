package umc.study.service.missionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 특정 가게에 미션을 추가하는 비즈니스 로직
    @Transactional
    @Override
    public Long addMission(MissionRequestDTO.CreateMissionDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
            .store(store)
            .missionSpec(request.getMissionSpec())
            .deadline(request.getDeadline())
            .build();

        // 미션 엔터티 저장
        missionRepository.save(mission);
        return mission.getId();
    }

    //미션 시작 비즈니스 로직
    @Transactional
    @Override
    public Long startMission(MissionRequestDTO.StartMissionDTO request) {
        // 시작하기 전, 미션의 시작여부 확인
        Long missionId = request.getMissionId();

        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new RuntimeException("해당 미션은 존재하지 않습니다."));

        Member member = memberRepository.findById(request.getMemberId())
            .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        MemberMission memberMission = MemberMission.builder()
            .member(member)
            .mission(mission)
            .status(MissionStatus.CHALLENGING)
            .build();

        memberMissionRepository.save(memberMission);

        return memberMission.getId();
    }
}

