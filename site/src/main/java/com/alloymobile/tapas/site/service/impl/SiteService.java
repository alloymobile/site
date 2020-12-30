package com.alloymobile.tapas.site.service.impl;

import com.alloymobile.tapas.site.persistence.entity.ISiteDBO;
import com.alloymobile.tapas.site.persistence.repository.ISiteJpaRepository;
import com.alloymobile.tapas.site.service.SiteRepositoryService;
import com.alloymobile.tapas.site.service.dto.ISiteDTO;
import com.alloymobile.tapas.site.service.mapper.SiteMapper;
import com.alloymobile.tapas.site.service.mapper.SiteMapperPair;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

@Getter
public abstract class SiteService<DBO_TYPE extends ISiteDBO, DTO_TYPE extends ISiteDTO> extends SiteRepositoryService<DBO_TYPE> {

    protected final SiteMapper<DBO_TYPE,DTO_TYPE> siteMapper;

    public SiteService(ISiteJpaRepository iSiteJpaRepository, SiteMapper<DBO_TYPE, DTO_TYPE> siteMapper) {
        super(iSiteJpaRepository);
        this.siteMapper = siteMapper;
    }

    protected Optional<ResponseEntity<DTO_TYPE>> readById(Long id) {
        return super.findById(id).map(this.siteMapper::toDTO);
    }


    protected Optional<ResponseEntity<Page<DTO_TYPE>>> readAll(Predicate predicate, Pageable pageable) {
        return super.findAll(predicate,pageable).map(this.siteMapper::toDTOs);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> createOne(DBO_TYPE dbo_type) {
        return super.save(dbo_type).map(this.siteMapper::toDTO);
    }

    protected Optional<ResponseEntity<DTO_TYPE>> update(DBO_TYPE dboToUpdate,DTO_TYPE updatedObject) {
        if (dboToUpdate == null || updatedObject == null) {
            return Optional.empty();
        }
        this.siteMapper.extendForUpdate(Collections.singletonList(new SiteMapperPair<>(dboToUpdate, updatedObject)));
        return this.save(dboToUpdate).map(this.siteMapper::toDTO);
    }

}
