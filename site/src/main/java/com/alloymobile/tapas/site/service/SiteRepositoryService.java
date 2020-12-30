package com.alloymobile.tapas.site.service;

import com.alloymobile.tapas.site.persistence.entity.ISiteDBO;
import com.alloymobile.tapas.site.persistence.repository.ISiteJpaRepository;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@Getter
public abstract class SiteRepositoryService<DBO_TYPE extends ISiteDBO> {
    protected final ISiteJpaRepository<DBO_TYPE> repository;

    public SiteRepositoryService(ISiteJpaRepository iSiteJpaRepository){
        this.repository = iSiteJpaRepository;
    }

    //find an object by id and return dbo object
    public Optional<DBO_TYPE> findById(Long id){
        if(id == null){
            return Optional.empty();
        }
        return this.repository.findById(id);
    }

    protected Optional<Page<DBO_TYPE>> findAll(Predicate predicate, Pageable pageable) {
        if (null == pageable) {
            return Optional.empty();
        }
        final Page<DBO_TYPE> page;
        if (predicate == null) {
            page = this.getRepository().findAll(pageable);
        } else {
            page = this.getRepository().findAll(predicate, pageable);
        }
        return Optional.of(page);
    }

    protected Optional<List<DBO_TYPE>> save(Iterable<DBO_TYPE> iterable) {
        if (iterable == null || !iterable.iterator().hasNext()) {
            return Optional.empty();
        }
        return Optional.of(this.repository.saveAll(iterable));
    }

    protected Optional<DBO_TYPE> save(DBO_TYPE dbo_type) {
        if (dbo_type == null) {
            return Optional.empty();
        }
        return Optional.of(this.repository.save(dbo_type));
    }

    //delete an object by ID
    protected void deleteById(Long id) {
        if (id != null) {
            this.repository.findById(id).ifPresent(this::delete);
        }
    }

    //Method only used in this class
    private void delete(DBO_TYPE dbo_type) {
        if (dbo_type != null) {
            this.repository.delete(dbo_type);
        }
    }

}
