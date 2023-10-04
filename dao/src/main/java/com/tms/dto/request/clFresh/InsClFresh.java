package com.tms.dto.request.clFresh;

import com.fasterxml.jackson.databind.JsonNode;
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
public class InsClFresh {
    @NotNull
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
    private JsonNode attribute;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String createDate;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String modifyDate;
    private Integer modifyBy;
    private Integer cpId;
    private Integer callingListId;
    private String leadType;
    private String agcLeadAddress;
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
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String lastCallTime;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String nextCallTime;
    private Integer numberOfDay;
    private Integer attemptBusy;
    private Integer attemptNoans;
    private Integer attemptUnreachable;
    private Integer attemptOther1;
    private Integer attemptOther2;
    private Integer attemptOther3;
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
    private Integer clGroup;
    private String in_agc_offer_id;
    private String assignedName;
    private String calledByName;
    private String callingListName;
    private String campaignName;
    private String lastCallStatusName;
    private String leadStatusName;
    private String source;
    private String user_defin_03;
    private String neighborhood;
    private String postalCode;
    private Integer trackerId;
    private String agentNote;
    private Integer actualCall;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date.Pattern.message}")
    private String firstCallTime;
    private Integer firstCallBy;
    private Integer firstCallStatus;
    private String firstCallReason;
    private String firstCallComment;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date.Pattern.message}")
    private String fcrTime;
    private Integer fcrBy;
    private Integer fcrStatus;
    private String fcrReason;
    private String fcrComment;
    private Integer crmActionType;
    private Integer team;
    private Integer teamSupervisor;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date.Pattern.message}")
    private String appointmentDate;
    private Integer agentGroupId;
    private Integer minSkillLevel;
    private Integer agentSkillLevel;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date.Pattern.message}")
    private String firstCallClick;
    private Integer agentHold;
    private Integer assignedGroupId;
    private String bdrActionType;
    private Integer priority;
    private Integer referenceLeadId;
    private String deliveryPackageCode;
    private String ipAddress;
    private String ipCountry;
    private String ipRegion;
    private String ipCity;
    private Integer referenceOrgId;
    private String batchId;
    private Integer leadSequence;
    private Integer batchSequence;
    private Integer freshLeadId;
    private Integer freshOrgId;
    private String postbackStatus;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date.Pattern.message}")
    private String postbackDate;
    private Integer validatorUserId;
    private String revisedLeadName;
    private Boolean isReassignedValidator;
    private String paymentTransactionId;

    @Override
    public String toString() {
        return (
                "(" + leadId +
                        "," + agcId +
                        "," + (agcCode == null ? "null" : "'" + agcCode + "'") +
                        "," + orgId +
                        "," + ccCode +
                        "," + (name == null ? "null" : "'" + name + "'") +
                        "," + (phone == null ? "null" : "'" + phone + "'") +
                        "," + prodId +
                        "," + (prodName == null ? "null" : "'" + prodName + "'") +
                        "," + assigned +
                        "," + calledBy +
                        "," + (address == null ? "null" : "'" + address + "'") +
                        "," + (province == null ? "null" : "'" + province + "'") +
                        "," + (district == null ? "null" : "'" + district + "'") +
                        "," + (subdistrict == null ? "null" : "'" + subdistrict + "'") +
                        "," + (comment == null ? "null" : "'" + comment + "'") +
                        "," + lastCallStatus +
                        "," + (dayCall==null?0:dayCall) +
                        "," + (totalCall==null?0:totalCall) +
                        "," + (amount == null ? "null" : "'" + amount + "'") +
                        "," + leadStatus +
                        "," + result +
                        "," + (userDefin01 == null ? "null" : "'" + userDefin01 + "'") +
                        "," + (userDefin02 == null ? "null" : "'" + userDefin02 + "'") +
                        "," + (userDefin03 == null ? "null" : "'" + userDefin03 + "'") +
                        "," + (userDefin04 == null ? "null" : "'" + userDefin04 + "'") +
                        "," + (userDefin05 == null ? "null" : "'" + userDefin05 + "'") +
                        "," + (attribute == null ? "null" : "'" + attribute + "'") +
                        "," + (createDate == null ? "null" : "'" + createDate + "'") +
                        "," + (modifyDate == null ? "null" : "'" + modifyDate + "'") +
                        "," + modifyBy +
                        "," + cpId +
                        "," + callingListId +
                        "," + (leadType == null ? "null" : "'" + leadType + "'") +
                        "," + (agcLeadAddress == null ? "null" : "'" + agcLeadAddress + "'") +
                        "," + (otherName1 == null ? "null" : "'" + otherName1 + "'") +
                        "," + (otherPhone1 == null ? "null" : "'" + otherPhone1 + "'") +
                        "," + (otherName2 == null ? "null" : "'" + otherName2 + "'") +
                        "," + (otherPhone2 == null ? "null" : "'" + otherPhone2 + "'") +
                        "," + (otherName3 == null ? "null" : "'" + otherName3 + "'") +
                        "," + (otherPhone3 == null ? "null" : "'" + otherPhone3 + "'") +
                        "," + (otherName4 == null ? "null" : "'" + otherName4 + "'") +
                        "," + (otherPhone4 == null ? "null" : "'" + otherPhone4 + "'") +
                        "," + (otherName5 == null ? "null" : "'" + otherName5 + "'") +
                        "," + (otherPhone5 == null ? "null" : "'" + otherPhone5 + "'") +
                        "," + (lastCallTime == null ? "null" : "'" + lastCallTime + "'") +
                        "," + (nextCallTime == null ? "null" : "'" + nextCallTime + "'") +
                        "," + numberOfDay +
                        "," + attemptBusy +
                        "," + attemptNoans +
                        "," + attemptUnreachable +
                        "," + attemptOther1 +
                        "," + attemptOther2 +
                        "," + attemptOther3 +
                        "," + (clickId == null ? "null" : "'" + clickId + "'") +
                        "," + (affiliateId == null ? "null" : "'" + affiliateId + "'") +
                        "," + (subid1 == null ? "null" : "'" + subid1 + "'") +
                        "," + (subid2 == null ? "null" : "'" + subid2 + "'") +
                        "," + (subid3 == null ? "null" : "'" + subid3 + "'") +
                        "," + (subid4 == null ? "null" : "'" + subid4 + "'") +
                        "," + (subid5 == null ? "null" : "'" + subid5 + "'") +
                        "," + (networkId == null ? "null" : "'" + networkId + "'") +
                        "," + (pid == null ? "null" : "'" + pid + "'") +
                        "," + (trackingUrlId == null ? "null" : "'" + trackingUrlId + "'") +
                        "," + (offerId == null ? "null" : "'" + offerId + "'") +
                        "," + (agcOfferId == null ? "null" : "'" + agcOfferId + "'") +
                        "," + (terms == null ? "null" : "'" + terms + "'") +
                        "," + (price == null ? "null" : "'" + price + "'") +
                        "," + (unit == null ? "null" : "'" + unit + "'") +
                        "," + customerAge +
                        "," + (customerEmail == null ? "null" : "'" + customerEmail + "'") +
                        "," + (customerComment == null ? "null" : "'" + customerComment + "'") +
                        "," + (internalComment == null ? "null" : "'" + internalComment + "'") +
                        "," + (carrierComment == null ? "null" : "'" + carrierComment + "'") +
                        "," + clGroup +
                        "," + (in_agc_offer_id == null ? "null" : "'" + in_agc_offer_id + "'") +
                        "," + (assignedName == null ? "null" : "'" + assignedName + "'") +
                        "," + (calledByName == null ? "null" : "'" + calledByName + "'") +
                        "," + (callingListName == null ? "null" : "'" + callingListName + "'") +
                        "," + (campaignName == null ? "null" : "'" + campaignName + "'") +
                        "," + (lastCallStatusName == null ? "null" : "'" + lastCallStatusName + "'") +
                        "," + (leadStatusName == null ? "null" : "'" + leadStatusName + "'") +
                        "," + (source == null ? "null" : "'" + source + "'") +
                        "," + (user_defin_03 == null ? "null" : "'" + user_defin_03 + "'") +
                        "," + (neighborhood == null ? "null" : "'" + neighborhood + "'") +
                        "," + (postalCode == null ? "null" : "'" + postalCode + "'") +
                        "," + trackerId +
                        "," + (agentNote == null ? "null" : "'" + agentNote + "'") +
                        "," + actualCall +
                        "," + (firstCallTime == null ? "null" : "'" + firstCallTime + "'") +
                        "," + firstCallBy +
                        "," + firstCallStatus +
                        "," + (firstCallReason == null ? "null" : "'" + firstCallReason + "'") +
                        "," + (firstCallComment == null ? "null" : "'" + firstCallComment + "'") +
                        "," + (fcrTime == null ? "null" : "'" + fcrTime + "'") +
                        "," + fcrBy +
                        "," + fcrStatus +
                        "," + (fcrReason == null ? "null" : "'" + fcrReason + "'") +
                        "," + (fcrComment == null ? "null" : "'" + fcrComment + "'") +
                        "," + crmActionType +
                        "," + team +
                        "," + teamSupervisor +
                        "," + (appointmentDate == null ? "null" : "'" + appointmentDate + "'") +
                        "," + agentGroupId +
                        "," + minSkillLevel +
                        "," + agentSkillLevel +
                        "," + (firstCallClick == null ? "null" : "'" + firstCallClick + "'") +
                        "," + agentHold +
                        "," + assignedGroupId +
                        "," + (bdrActionType == null ? "null" : "'" + bdrActionType + "'") +
                        "," + priority +
                        "," + referenceLeadId +
                        "," + (deliveryPackageCode == null ? "null" : "'" + deliveryPackageCode + "'") +
                        "," + (ipAddress == null ? "null" : "'" + ipAddress + "'") +
                        "," + (ipCountry == null ? "null" : "'" + ipCountry + "'") +
                        "," + (ipRegion == null ? "null" : "'" + ipRegion + "'") +
                        "," + (ipCity == null ? "null" : "'" + ipCity + "'") +
                        "," + referenceOrgId +
                        "," + (batchId == null ? "null" : "'" + batchId + "'") +
                        "," + leadSequence +
                        "," + batchSequence +
                        "," + freshLeadId +
                        "," + freshOrgId +
                        "," + (postbackStatus == null ? "null" : "'" + postbackStatus + "'") +
                        "," + (postbackDate == null ? "null" : "'" + postbackDate + "'") +
                        "," + validatorUserId +
                        "," + (revisedLeadName == null ? "null" : "'" + revisedLeadName + "'") +
                        "," + isReassignedValidator +
                        "," + (paymentTransactionId == null ? "null" : "'" + paymentTransactionId + "'") + ")"
        );
    }

}
