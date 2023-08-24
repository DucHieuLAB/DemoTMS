package com.tms.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "lc_district")
public class District {
    @Id
    @Column(name = "dt_id")
    private Integer id;
    @Column(name = "prv_id")
    private Integer prvId;
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

    public District() {
    }

    public District(Integer id, String name, String shortName, String code, String dcsr, Integer status) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.code = code;
        this.dcsr = dcsr;
        this.status = status;
    }
}
