package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import umc.study.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    // 지역 이름으로 Region을 조회하는 메서드
    Optional<Region> findByName(String name);
}
