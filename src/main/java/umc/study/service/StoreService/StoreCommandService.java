package umc.study.service.StoreService;

import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
	public Long addStore(StoreRequestDTO.AddStoreDTO request);
}
