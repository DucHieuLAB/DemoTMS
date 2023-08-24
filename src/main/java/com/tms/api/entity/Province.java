package com.tms.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "lc_province")
public class Province {
    @Id
    @Column(name = "prv_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "shortname")
    private String shortname;
    @Column(name = "code")
    private String code;
    @Column(name = "dcsr")
    private String dcsr;
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "status")
    private Integer status;

    public Province() {
    }

}
