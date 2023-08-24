package com.tms.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "bp_partner")
public class Partner extends BaseObject {
    @Id
    @Column(name = "pn_id")
    private Integer id;
    @Column(name = "pn_type")
    private Integer pnType;
    @Column(name = "name")
    private String name;
    @Column(name = "shortname")
    private String shortName;
    @Column(name = "contact")
    private String contact;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "modifyby")
    private Integer modifyBy;
    @Column(name = "modifydate")
    private Timestamp modifyDate;
    @Column(name = "logo_path_01")
    private String logoPath01;
    @Column(name = "logo_path_02")
    private String logoPath02;
    @Column(name = "type")
    private Integer type;
    @Column(name = "skill_level")
    private Integer skillLevel;
    @Column(name = "org_id")
    private Integer orgId;

    public Partner() {
    }

    public Partner(Integer id, Integer pnType, String name, String shortName, String contact, String phone, String email, String address, Integer modifyBy, Timestamp modifyDate, String logoPath01, String logoPath02, Integer type, Integer skillLevel, Integer orgId) {
        this.id = id;
        this.pnType = pnType;
        this.name = name;
        this.shortName = shortName;
        this.contact = contact;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.logoPath01 = logoPath01;
        this.logoPath02 = logoPath02;
        this.type = type;
        this.skillLevel = skillLevel;
        this.orgId = orgId;
    }
}
