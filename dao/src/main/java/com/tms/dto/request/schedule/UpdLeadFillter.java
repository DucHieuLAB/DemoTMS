package com.tms.dto.request.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.tms.DaoConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdLeadFillter {
    @NotNull
    private Integer[] leadids;
    private Integer agcId;
    private String agcCode;
    private Integer orgId;
    private String ccCode;
    private String name;
    private String phone;
    private Integer prodId;
    private String prodName;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String comment;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String in_createdate;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String modifydate;
    private Integer modifyby;
    private Integer status;
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
    private Integer customerAge;
    private String customerEmail;
    private String customerComment;
    private String agcoffer_id;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String createDate;
    private String linkClickId;
    private String duplicateMainLead;
    private String leadIp;
    private Integer duplicateTime;
    private Integer trackerId;
    private String ipAddress;
}
