package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDTO {
        @NotBlank
        private String name;

        @NotBlank
        private String address;

        @NotNull
        private Long regionId; // 특정 지역 ID
    }
}
