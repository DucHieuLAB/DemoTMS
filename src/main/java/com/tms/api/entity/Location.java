package com.tms.api.entity;

import org.apache.commons.pool2.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_location")
public class Location extends BaseObject {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "create_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modifi_date")
    private Timestamp modifyDate;

    @Column(name = "uuid_key")
    private String uuidKey;

    @Column(name = "voided")
    private Boolean voided;

    @Column(name = "code")
    private String code;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column (name = "name")
    private String name;

    public Location() {
    }

    public Location(Integer id, Timestamp createDate, String createdBy, String modifiedBy, Timestamp modifyDate, String uuidKey, Boolean voided, String code, Float latitude, Float longitude, String name) {
        this.id = id;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifyDate = modifyDate;
        this.uuidKey = uuidKey;
        this.voided = voided;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
