package umc.study.service.MissionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

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
}
