package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import umc.study.validation.annotation.StoreExists;

public class ReviewRequestDTO {

    @Getter
    @Builder
    public static class AddReviewDTO {
        @StoreExists
        private Long storeId;

        @NotBlank
        private String content;

        @NotNull
        private Integer rating; // 1~5
    }
}
