package com.alloymobile.tapas.site.service.impl.page;

import com.alloymobile.tapas.site.exception.NotFoundException;
import com.alloymobile.tapas.site.persistence.entity.Page;
import com.alloymobile.tapas.site.persistence.repository.ISiteJpaRepository;
import com.alloymobile.tapas.site.persistence.repository.PageRepository;
import com.alloymobile.tapas.site.service.impl.SiteService;
import com.alloymobile.tapas.site.service.impl.page.dto.PageDTO;
import com.alloymobile.tapas.site.service.impl.page.mapper.PageMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService extends SiteService<Page, PageDTO> {

    public PageService(PageRepository repository,PageMapper pageMapper) {
        super(repository,pageMapper);
    }

    public Optional<ResponseEntity<PageDTO>> readPageById(Long id){
        return super.readById(id);
    }


    public Optional<ResponseEntity<PageDTO>> createPage(PageDTO newObject) {
        return super.createOne(this.getSiteMapper().toNewDBO(newObject));
    }

    public Optional<ResponseEntity<PageDTO>> updatePageById(Long id,PageDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }

    public void deletePageById(Long id) {
        super.deleteById(id);
    }
}
