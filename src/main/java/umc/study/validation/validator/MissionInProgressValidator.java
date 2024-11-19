package umc.study.validation.validator;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.validation.annotation.InProgressMission;

@Component
@RequiredArgsConstructor
public class MissionInProgressValidator implements ConstraintValidator<InProgressMission, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;




    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId=1L;
        boolean missionExists = missionRepository.existsById(missionId);
        if (!missionExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 미션은 존재하지 않습니다.")
                    .addConstraintViolation();
            return false;
        }
        boolean isInProgress = memberMissionRepository.existsByMemberAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING);

        if (isInProgress) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 미션은 이미 진행 중입니다.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}