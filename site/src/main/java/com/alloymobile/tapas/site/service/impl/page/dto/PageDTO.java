package com.alloymobile.tapas.site.service.impl.page.dto;

import com.alloymobile.tapas.site.service.dto.ISiteDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PageDTO implements ISiteDTO {
    private Long id;
    private String Name;
    private Date createDate;
    private String detail;
    private List<ResourceDTO> resourceList;
}
