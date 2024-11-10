package umc.study.service.memberService;

import java.util.Optional;

import umc.study.domain.Member;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

}