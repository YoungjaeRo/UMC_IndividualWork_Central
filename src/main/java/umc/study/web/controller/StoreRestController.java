package umc.study.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.storeService.StoreCommandService;
import umc.study.web.dto.store.StoreRequestDto;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;


@Operation(summary = "특정 지역에 가게 추가")
@PostMapping("/add")
    public ApiResponse<Long> addStore(@RequestBody @Valid StoreRequestDto.CreateStoreDTO request) {
        Long storeId = storeCommandService.addStore(request);

        return ApiResponse.onSuccess(storeId);
    }
}
