package umc.study.service.MissionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    @Override
    public Mission addMission(MissionRequestDTO.AddMissionDTO request) {
        // Store 존재 여부 확인
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        // Mission 생성 및 저장
        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public MissionResponseDTO.CompletedMissionDTO completeMission(Long missionId) {
        MemberMission memberMission = memberMissionRepository.findById(missionId)
            .orElseThrow(() -> new EntityNotFoundException("해당 미션을 찾을 수 없습니다."));
        if (memberMission.getStatus() != MissionStatus.IN_PROGRESS) {
            throw new IllegalStateException("현재 진행 중인 상태가 아닌 미션은 완료로 변경할 수 없습니다.");
        }
        memberMission.setStatus(MissionStatus.COMPLETE);

        return MissionResponseDTO.CompletedMissionDTO.builder()
            .missionId(memberMission.getId())
            .missionName(memberMission.getMission().getName())
            .status(memberMission.getStatus().name())
            .build();
    }
}
