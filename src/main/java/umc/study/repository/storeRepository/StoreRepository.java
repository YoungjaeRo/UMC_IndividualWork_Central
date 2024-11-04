package umc.study.repository.storeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}