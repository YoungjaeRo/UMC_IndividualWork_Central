package umc.study.repository.storeRepository;

import umc.study.domain.Store;

import java.util.List;


public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}