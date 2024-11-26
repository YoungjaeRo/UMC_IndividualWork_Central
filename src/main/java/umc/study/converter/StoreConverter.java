package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.AddStoreDTO request, Region region) {
        return Store.builder()
            .name(request.getName())
            .address(request.getAddress())
            .region(region)
            .build();
    }
}
