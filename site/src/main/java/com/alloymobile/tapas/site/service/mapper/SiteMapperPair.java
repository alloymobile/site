package com.alloymobile.tapas.site.service.mapper;

import com.alloymobile.tapas.site.persistence.entity.ISiteDBO;
import com.alloymobile.tapas.site.service.dto.ISiteDTO;


public class SiteMapperPair<DBO_TYPE extends ISiteDBO, DTO_TYPE extends ISiteDTO> {
    public final DBO_TYPE dbo;
    public final DTO_TYPE dto;

    public SiteMapperPair(DBO_TYPE dbo_type, DTO_TYPE dto_type) {
        this.dbo = dbo_type;
        this.dto = dto_type;
    }
}
