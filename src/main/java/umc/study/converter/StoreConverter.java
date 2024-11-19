package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.store.StoreRequestDto;

public class StoreConverter {
	public static Store toStore(StoreRequestDto.CreateStoreDTO request, Region region) {
		return Store.builder()
			.name(request.getName())
			.region(region)
			.address(request.getAddress())
			.score(request.getScore())
			.build();
	}
}
