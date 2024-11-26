package umc.study.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.StoreRequestDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @Operation(summary = "특정 지역에 가게 추가",
        description = "특정 지역에 새로운 가게를 추가합니다. 요청 시 가게의 지역 ID와 정보를 제공해야 합니다.")
    @PostMapping("/add")
    public ApiResponse<Long> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDTO request) {
        Long storeId = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(storeId);
    }
}
