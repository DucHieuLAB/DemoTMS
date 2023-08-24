package com.tms.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cf_default_delivery")
public class DefaultDelivery {
    @Id
    @Column(name = "cf_default_do_id")
    private Integer cfDefaultDoId;
    @Column(name = "org_id")
    private Integer orgId;
    @Column(name = "province_id")
    private Integer provinceId;
    @Column(name = "partner_id")
    private Integer partnerId;
    @Column(name = "lastmile_id")
    private Integer lastMileId;
    @Column(name = "warehouse_id")
    private Integer wareHouseId;
    @Column(name = "createby")
    private Integer createBy;
    @Column(name = "createdate")
    private Timestamp createDate;
    @Column(name = "modifyby")
    private Integer modifyBy;
    @Column(name = "modifydate")
    private Timestamp modifyDate;
    @Column(name = "pickup_id")
    private Integer pickupId;

    public DefaultDelivery() {

    }
}
