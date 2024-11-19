package umc.study.service.storeService;

import umc.study.web.dto.store.StoreRequestDto;

public interface StoreCommandService {
    Long addStore(StoreRequestDto.CreateStoreDTO request);
}
