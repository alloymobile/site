package com.alloymobile.tapas.site.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"pageResourceList"})
public class Page implements ISiteDBO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date createDate;

    private String detail;

    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("page")
    private List<PageResource> pageResourceList;
}
