package umc.study.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.ErrorReasonDTO;
import umc.study.apiPayload.code.status.ErrorStatus;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public ErrorReasonDTO getErrorReason() {
        return this.errorStatus.getReason(); // ErrorStatus에 정의된 메시지 반환
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.errorStatus.getReasonHttpStatus(); // HTTP 상태 코드 포함된 에러 메시지 반환
    }
}