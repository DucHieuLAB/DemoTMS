package com.tms.dto.request.lead;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClBasket {
    private int leadId;
    private int agcId;
    private String agcCode;
    private int orgId;
    private String ccCode;
    private String name;
    private String phone;
    private int prodId;
    private String prodName;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String comment;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private int modifyBy;
    private int status;
    private String email;
    private String quantity;
    private String clickId;
    private String agKey;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String amount;
    private String offerId;
    private String agcOfferId;
    private String terms;
    private String price;
    private String unit;
    private String clickId2;
    private String affiliateId;
    private String subid3;
    private String subid4;
    private String subid5;
    private String networkid;
    private String pid;
    private String trackingUrlId;
    private String subid1;
    private String subid2;
    private int customerAge;
    private String customerEmail;
    private String customerComment;
    private String in_agc_offer_id;
    private Timestamp create_date;
    private String linkClickId;
    private String duplicateMainLead;
    private String leadIp;
    private int duplicateTime;
    private int trackerId;
    private String ipAddress;
}
