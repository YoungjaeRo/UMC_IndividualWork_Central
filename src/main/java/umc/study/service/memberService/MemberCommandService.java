package umc.study.service.memberService;

import umc.study.domain.Member;
import umc.study.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
}
