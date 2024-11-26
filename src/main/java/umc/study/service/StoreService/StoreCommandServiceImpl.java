package umc.study.service.StoreService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    @Override
    public Long addStore(StoreRequestDTO.AddStoreDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));
        
        Store store = StoreConverter.toStore(request, region);
        storeRepository.save(store);
        return store.getId();
    }
}
