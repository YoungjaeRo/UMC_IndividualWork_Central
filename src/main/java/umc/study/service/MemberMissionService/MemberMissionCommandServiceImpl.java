package umc.study.service.MemberMissionService;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.web.dto.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    @Override
    public MemberMission addMissionChallenge(MemberMissionRequestDTO.AddMissionDTO request) {
        // 미션 존재 여부 확인
        Mission mission = missionRepository.findById(request.getMissionId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        // 회원 존재 여부 확인
        Member member = memberRepository.findById(request.getMemberId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 이미 도전 중인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionId(request.getMemberId(), request.getMissionId())) {
            throw new GeneralException(ErrorStatus.ALREADY_CHALLENGED);
        }

        // MemberMission 도전 기록 생성
        MemberMission missionChallenge = MemberMission.builder()
            .member(member)
            .mission(mission)
            .challengedAt(LocalDateTime.now())
            .status(MissionStatus.IN_PROGRESS)  // 미션 진행 중으로 설정
            .build();

        return memberMissionRepository.save(missionChallenge);
    }
}
