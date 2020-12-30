package com.alloymobile.tapas.site.service.mapper;

import com.alloymobile.tapas.site.exception.InternalServerException;
import com.alloymobile.tapas.site.exception.NotFoundException;
import com.alloymobile.tapas.site.persistence.entity.ISiteDBO;
import com.alloymobile.tapas.site.service.dto.ISiteDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;


public abstract class SiteMapper<DBO_TYPE extends ISiteDBO, DTO_TYPE extends ISiteDTO> {

    protected abstract void populateDBO(DBO_TYPE dbo, DTO_TYPE dto);

    protected abstract DTO_TYPE toDTOImpl(DBO_TYPE dbo);

    public DBO_TYPE toNewDBO(DTO_TYPE dto) {
        final DBO_TYPE newDBOInstance = this.getNewDBOInstance();
        this.populateDBO(newDBOInstance, dto);
        return newDBOInstance;
    }

    public ResponseEntity<DTO_TYPE> toDTO(DBO_TYPE dbo) {
        return new ResponseEntity<>(toDTOImpl(dbo), HttpStatus.OK);
    }

    private DTO_TYPE toDto(DBO_TYPE dbo) {
        return toDTOImpl(dbo);
    }

    public ResponseEntity<Page<DTO_TYPE>> toDTOs(Page<DBO_TYPE> dbos) {
        Page<DTO_TYPE> collect = dbos.map(this::toDto);
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    public void extendForUpdate(Collection<SiteMapperPair<DBO_TYPE, DTO_TYPE>> siteMappedPair) {
        siteMappedPair.forEach(mcqMappedPair -> this.populateDBO(mcqMappedPair.dbo, mcqMappedPair.dto));
    }

    private Class<DBO_TYPE> getDBOClass() {
        final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (Class<DBO_TYPE>) Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new InternalServerException("Unable to get DBO type from mapper");
        }
    }

    private DBO_TYPE getNewDBOInstance() {
        try {
            return getDBOClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InternalServerException("Unable to instantiate class from mapper");
        }
    }

}
