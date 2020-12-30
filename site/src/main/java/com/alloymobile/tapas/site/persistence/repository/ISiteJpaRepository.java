package com.alloymobile.tapas.site.persistence.repository;

import com.alloymobile.tapas.site.persistence.entity.ISiteDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ISiteJpaRepository<DBO_TYPE extends ISiteDBO> extends JpaRepository<DBO_TYPE,Long>, QuerydslPredicateExecutor<DBO_TYPE> {
}
