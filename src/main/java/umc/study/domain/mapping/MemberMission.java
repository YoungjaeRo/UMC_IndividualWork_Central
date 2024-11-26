package umc.study.domain.mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.common.BaseEntity;

import jakarta.persistence.*;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.MissionStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private LocalDateTime challengedAt;

    private LocalDate startDate; // 시작 날짜
    private LocalDate dueDate;   // 종료 날짜
}
