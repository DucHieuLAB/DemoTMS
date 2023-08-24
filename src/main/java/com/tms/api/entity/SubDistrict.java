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
@Table(name = "lc_subdistrict")
public class SubDistrict {
    @Id
    @Column(name = "sdt_id")
    private Integer id;
    @Column(name = "dt_id")
    private Integer dtId;
    @Column(name = "name")
    private String name;
    @Column(name = "shortname")
    private String shortName;
    @Column(name = "code")
    private String code;
    @Column(name = "dcsr")
    private String dcsr;
    @Column(name = "status")
    private Integer status;

    public SubDistrict() {
    }
}
