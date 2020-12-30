package com.alloymobile.tapas.site.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"resourcePageList"})
public class Resource implements ISiteDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    private Date createDate;

    private String detail;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("resource")
    private List<PageResource> resourcePageList;
}
