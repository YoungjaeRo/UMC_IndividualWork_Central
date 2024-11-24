package umc.study.validation.validator;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.validation.annotation.StoreExists;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<StoreExists, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return storeRepository.existsById(value);
    }
}
