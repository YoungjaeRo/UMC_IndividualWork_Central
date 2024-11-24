package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.validation.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            return false; // 유효하지 않은 경우
        }
        return true; // 유효한 경우
    }

    @Override
    public void initialize(ValidPage constraintAnnotation) {
        // 초기화 로직 필요 시 추가 할 예정
    }
}