package com.alloymobile.tapas.site.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"page","resource"})
public class PageResource implements ISiteDBO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="page_id")
    @JsonIgnoreProperties("pageResourceList")
    private Page page;

    @ManyToOne
    @JoinColumn(name="resource_id")
    @JsonIgnoreProperties("resourcePageList")
    private Resource resource;
}
