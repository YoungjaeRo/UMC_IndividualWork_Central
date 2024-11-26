package umc.study.validation.validator;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.study.repository.MemberMissionRepository;
import umc.study.validation.annotation.ExistMemberMission;

@Component
@RequiredArgsConstructor
public class MemberMissionValidator implements ConstraintValidator<ExistMemberMission, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        if (missionId == null) {
            return false;
        }
        // 데이터베이스에서 해당 미션 ID가 존재하는지 확인
        return memberMissionRepository.existsById(missionId);
    }

    @Override
    public void initialize(ExistMemberMission constraintAnnotation) {
        // 초기화가 필요하다면 작성할 예정
    }
}
