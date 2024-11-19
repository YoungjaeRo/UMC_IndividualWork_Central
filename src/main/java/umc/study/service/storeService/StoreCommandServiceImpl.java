package umc.study.service.storeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RegionHandler;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.store.StoreRequestDto;


@Service
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public StoreCommandServiceImpl(StoreRepository storeRepository, RegionRepository regionRepository) {
        this.storeRepository = storeRepository;
        this.regionRepository = regionRepository;
    }

    @Transactional
    @Override
    public Long addStore(StoreRequestDto.CreateStoreDTO request) {
        Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new RegionHandler(
            ErrorStatus.REGION_NOT_FOUND));

        Store store = StoreConverter.toStore(request, region);

        storeRepository.save(store);

        return store.getId();
    }
}
