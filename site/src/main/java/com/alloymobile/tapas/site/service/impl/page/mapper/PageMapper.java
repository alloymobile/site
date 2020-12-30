package com.alloymobile.tapas.site.service.impl.page.mapper;

import com.alloymobile.tapas.site.persistence.entity.Page;
import com.alloymobile.tapas.site.service.impl.page.dto.PageDTO;
import com.alloymobile.tapas.site.service.mapper.SiteMapper;
import org.springframework.stereotype.Component;

@Component
public class PageMapper extends SiteMapper<Page, PageDTO> {

    @Override
    protected void populateDBO(Page dbo,PageDTO dto) {
        if(dto.getId() != null) {
            dbo.setId(dto.getId());
        }
        dbo.setName(dto.getName());
    }

    @Override
    protected PageDTO toDTOImpl(Page dbo) {
        PageDTO PageDTO = new PageDTO();
        PageDTO.setId(dbo.getId());
        PageDTO.setName(dbo.getName());
        return PageDTO;
    }
}
