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
public class UpdClCallback {
    private int leadId;
    private int agcId;
    private String agcCode;
    private String ccCode;
    private String name;
    private String phone;
    private int prodId;
    private String prodName;
    private int assigned;
    private int calledBy;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String comment;
    private int lastCallStatus;
    private int dayCall;
    private int totalCall;
    private String amount;
    private int leadStatus;
    private int result;
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
    private int owner;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String modifyDate;
    private int modifyBy;
    private int cpId;
    private int callingListId;
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
    private int numberOfDay;
    private int attemptBusy;
    private int attemptNoans;
    private int attemptUnreachable;
    private int attemptOther1;
    private int attemptOther2;
    private int attemptOther3;
    private String callbackName;
}
