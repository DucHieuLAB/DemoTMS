package com.tms.dto.request.clCallback;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tms.DaoConst;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsClCallback {
    private Integer leadId;
    private Integer agcId;
    private String agcCode;
    private Integer orgId;
    private String ccCode;
    private String name;
    private String phone;
    private Integer prodId;
    private String prodName;
    private Integer assigned;
    private Integer calledBy;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String comment;
    private Integer lastCallStatus;
    private Integer dayCall;
    private Integer totalCall;
    private String amount;
    private Integer leadStatus;
    private Integer result;
    private String userDefin01;
    private String userDefin02;
    private String userDefin03;
    private String userDefin04;
    private String userDefin05;
    private String attribute;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String callbackTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String requestTime;
    private Integer owner;
    private String createDate;
    private String modifyDate;
    private Integer modifyBy;
    private Integer cpId;
    private Integer callingListId;
    private String leadType;
    private String agcLeadAddress;
    private String callbackPhone;
    private String callbackNote;
    private String otherName1;
    private String otherPhone1;
    private String otherName2;
    private String otherPhone2;
    private String otherName3;
    private String otherPhone3;
    private String otherName4;
    private String otherPhone4;
    private String otherName5;
    private String otherPhone5;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String lastCallTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String nextCallTime;
    private Integer numberOfDay;
    private Integer attemptBusy;
    private Integer attemptNoAns;
    private Integer attemptUnreachable;
    private Integer attemptOther1;
    private Integer attemptOther2;
    private Integer attemptOther3;
    private String callbackName;
    private String clickId;
    private String affiliateId;
    private String subid1;
    private String subid2;
    private String subid3;
    private String subid4;
    private String subid5;
    private String networkId;
    private String pid;
    private String trackingUrlId;
    private String offerId;
    private String agcOfferId;
    private String terms;
    private String price;
    private String unit;
    private Integer customerAge;
    private String customerEmail;
    private String customerComment;
    private String internalComment;
    private String carrierComment;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String closeTime;
   @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String maxCloseTime;
}
