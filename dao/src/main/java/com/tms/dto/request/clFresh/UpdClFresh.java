package com.tms.dto.request.clFresh;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdClFresh {
    @NotNull
    @JsonProperty("in_lead_id")
    @JsonAlias("leadId")
    private Integer leadId;

    @JsonProperty("in_agc_id")
    @JsonAlias("agcId")
    private Integer agcId;

    @JsonProperty("in_agc_code")
    @JsonAlias("agcCode")
    private String agcCode;

    @JsonProperty("in_org_id")
    @JsonAlias("orgId")
    private Integer orgId;

    @JsonProperty("in_cc_code")
    @JsonAlias("ccCode")
    private String ccCode;

    @JsonProperty("in_name")
    @JsonAlias("name")
    private String name;

    @JsonProperty("in_phone")
    @JsonAlias("phone")
    private String phone;

    @JsonProperty("in_prod_id")
    @JsonAlias("prodId")
    private Integer prodId;

    @JsonProperty("in_prod_name")
    @JsonAlias("prodName")
    private String prodName;

    @JsonProperty("in_assigned")
    @JsonAlias("assigned")
    private Integer assigned;

    @JsonProperty("in_called_by")
    @JsonAlias("calledBy")
    private Integer calledBy;

    @JsonProperty("in_address")
    @JsonAlias("address")
    private String address;

    @JsonProperty("in_province")
    @JsonAlias("province")
    private String province;

    @JsonProperty("in_district")
    @JsonAlias("district")
    private String district;

    @JsonProperty("in_subdistrict")
    @JsonAlias("subdistrict")
    private String subdistrict;

    @JsonProperty("in_comment")
    @JsonAlias("comment")
    private String comment;

    @JsonProperty("in_last_call_status")
    @JsonAlias("lastCallStatus")
    private Integer lastCallStatus;

    @JsonProperty("in_day_call")
    @JsonAlias("dayCall")
    private Integer dayCall;

    @JsonProperty("in_total_call")
    @JsonAlias("totalCall")
    private Integer totalCall;

    @JsonProperty("in_amount")
    @JsonAlias("amount")
    private String amount;

    @JsonProperty("in_lead_status")
    @JsonAlias("leadStatus")
    private Integer leadStatus;

    @JsonProperty("in_result")
    @JsonAlias("result")
    private Integer result;

    @JsonProperty("in_userdefin01")
    @JsonAlias("userDefin01")
    private String userDefin01;

    @JsonProperty("in_userdefin02")
    @JsonAlias("userDefin02")
    private String userDefin02;

    @JsonProperty("in_userdefin03")
    @JsonAlias("userDefin03")
    private String userDefin03;

    @JsonProperty("in_userdefin04")
    @JsonAlias("userDefin04")
    private String userDefin04;

    @JsonProperty("in_userdefin05")
    @JsonAlias("userDefin05")
    private String userDefin05;

    @JsonProperty("in_attribute")
    @JsonAlias("attribute")
    private JsonNode attribute;

    @JsonProperty("in_create_date")
    @JsonAlias("createDate")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String createDate;

    @JsonProperty("in_modify_date")
    @JsonAlias("modifyDate")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String modifyDate;

    @JsonProperty("in_modify_by")
    @JsonAlias("modifyBy")
    private Integer modifyBy;

    @JsonProperty("in_cp_id")
    @JsonAlias("cpId")
    private Integer cpId;

    @JsonProperty("in_callinglist_id")
    @JsonAlias("callingListId")
    private Integer callingListId;

    @JsonProperty("in_lead_type")
    @JsonAlias("leadType")
    private String leadType;

    @JsonProperty("in_agc_lead_address")
    @JsonAlias("agcLeadAddress")
    private String agcLeadAddress;

    @JsonProperty("in_other_name1")
    @JsonAlias("otherName1")
    private String otherName1;

    @JsonProperty("in_other_phone1")
    @JsonAlias("otherPhone1")
    private String otherPhone1;

    @JsonProperty("in_other_name2")
    @JsonAlias("otherName2")
    private String otherName2;

    @JsonProperty("in_other_phone2")
    @JsonAlias("otherPhone2")
    private String otherPhone2;

    @JsonProperty("in_other_name3")
    @JsonAlias("otherName3")
    private String otherName3;

    @JsonProperty("in_other_phone3")
    @JsonAlias("otherPhone3")
    private String otherPhone3;

    @JsonProperty("in_other_name4")
    @JsonAlias("otherName4")
    private String otherName4;

    @JsonProperty("in_other_phone4")
    @JsonAlias("otherPhone4")
    private String otherPhone4;

    @JsonProperty("in_other_name5")
    @JsonAlias("otherName5")
    private String otherName5;

    @JsonProperty("in_other_phone5")
    @JsonAlias("otherPhone5")
    private String otherPhone5;

    @JsonProperty("in_last_call_time")
    @JsonAlias("lastCallTime")
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String lastCallTime;

    @JsonProperty("in_next_call_time")
    @JsonAlias("nextCallTime")
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String nextCallTime;

    @JsonProperty("in_number_of_day")
    @JsonAlias("numberOfDay")
    private Integer numberOfDay;

    @JsonProperty("in_attempt_busy")
    @JsonAlias("attemptBusy")
    private Integer attemptBusy;

    @JsonProperty("in_attempt_noans")
    @JsonAlias("attemptNoans")
    private Integer attemptNoans;

    @JsonProperty("in_attempt_unreachable")
    @JsonAlias("attemptUnreachable")
    private Integer attemptUnreachable;

    @JsonProperty("in_attempt_other1")
    @JsonAlias("attemptOther1")
    private Integer attemptOther1;

    @JsonProperty("in_attempt_other2")
    @JsonAlias("attemptOther2")
    private Integer attemptOther2;

    @JsonProperty("in_attempt_other3")
    @JsonAlias("attemptOther3")
    private Integer attemptOther3;

    @JsonProperty("in_click_id")
    @JsonAlias("clickId")
    private String clickId;

    @JsonProperty("in_affiliate_id")
    @JsonAlias("affiliateId")
    private String affiliateId;

    @JsonProperty("in_subid1")
    @JsonAlias("subid1")
    private String subid1;

    @JsonProperty("in_subid2")
    @JsonAlias("subid2")
    private String subid2;

    @JsonProperty("in_subid3")
    @JsonAlias("subid3")
    private String subid3;

    @JsonProperty("in_subid4")
    @JsonAlias("subid4")
    private String subid4;

    @JsonProperty("in_subid5")
    @JsonAlias("subid5")
    private String subid5;

    @JsonProperty("in_networkid")
    @JsonAlias("networkId")
    private String networkId;

    @JsonProperty("in_pid")
    @JsonAlias("pid")
    private String pid;

    @JsonProperty("in_tracking_url_id")
    @JsonAlias("trackingUrlId")
    private String trackingUrlId;

    @JsonProperty("in_offer_id")
    @JsonAlias("offerId")
    private String offerId;

    @JsonProperty("in_agc_offer_id")
    @JsonAlias("agcOfferId")
    private String agcOfferId;

    @JsonProperty("in_terms")
    @JsonAlias("terms")
    private String terms;

    @JsonProperty("in_price")
    @JsonAlias("price")
    private String price;

    @JsonProperty("in_unit")
    @JsonAlias("unit")
    private String unit;

    @JsonProperty("in_customer_age")
    @JsonAlias("customerAge")
    private Integer customerAge;

    @JsonProperty("in_customer_email")
    @JsonAlias("customerEmail")
    private String customerEmail;

    @JsonProperty("in_customer_comment")
    @JsonAlias("customerComment")
    private String customerComment;

    @JsonProperty("in_internal_comment")
    @JsonAlias("internalComment")
    private String internalComment;

    @JsonProperty("in_carrier_comment")
    @JsonAlias("carrierComment")
    private String carrierComment;

    @JsonProperty("in_cl_group")
    @JsonAlias("clGroup")
    private Integer clGroup;

    @JsonProperty("in_agcoffer_id")
    @JsonAlias("agcofferId")
    private String agcofferId;

    @JsonProperty("in_assigned_name")
    @JsonAlias("assignedName")
    private String assignedName;

    @JsonProperty("in_calledby_name")
    @JsonAlias("calledByName")
    private String calledByName;

    @JsonProperty("in_callinglist_name")
    @JsonAlias("callingListName")
    private String callingListName;

    @JsonProperty("in_campaign_name")
    @JsonAlias("campaignName")
    private String campaignName;

    @JsonProperty("in_lastcall_status_name")
    @JsonAlias("lastCallStatusName")
    private String lastCallStatusName;

    @JsonProperty("in_lead_status_name")
    @JsonAlias("leadStatusName")
    private String leadStatusName;

    @JsonProperty("in_source")
    @JsonAlias("source")
    private String source;

    @JsonProperty("in_userdefin_03")
    @JsonAlias("userDefin_03")
    private String userDefin_03;

    @JsonProperty("in_neighborhood")
    @JsonAlias("neighborhood")
    private String neighborhood;

    @JsonProperty("in_postal_code")
    @JsonAlias("postalCode")
    private String postalCode;

    @JsonProperty("in_tracker_id")
    @JsonAlias("trackerId")
    private Integer trackerId;

    @JsonProperty("in_agent_note")
    @JsonAlias("agentNote")
    private String agentNote;

    @JsonProperty("in_actual_call")
    @JsonAlias("actualCall")
    private Integer actualCall;

    @JsonProperty("in_first_call_time")
    @JsonAlias("firstCallTime")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String firstCallTime;

    @JsonProperty("in_first_call_by")
    @JsonAlias("firstCallBy")
    private Integer firstCallBy;

    @JsonProperty("in_first_call_status")
    @JsonAlias("firstCallStatus")
    private Integer firstCallStatus;

    @JsonProperty("in_first_call_reason")
    @JsonAlias("firstCallReason")
    private String firstCallReason;

    @JsonProperty("in_first_call_comment")
    @JsonAlias("firstCallComment")
    private String firstCallComment;

    @JsonProperty("in_fcr_time")
    @JsonAlias("fcrTime")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String fcrTime;

    @JsonProperty("in_fcr_by")
    @JsonAlias("fcrBy")
    private Integer fcrBy;

    @JsonProperty("in_fcr_status")
    @JsonAlias("fcrStatus")
    private Integer fcrStatus;

    @JsonProperty("in_fcr_reason")
    @JsonAlias("fcrReason")
    private String fcrReason;

    @JsonProperty("in_fcr_comment")
    @JsonAlias("fcrComment")
    private String fcrComment;

    @JsonProperty("in_crm_action_type")
    @JsonAlias("crmActionType")
    private Integer crmActionType;

    @JsonProperty("in_team")
    @JsonAlias("team")
    private Integer team;

    @JsonProperty("in_team_supervisor")
    @JsonAlias("teamSupervisor")
    private Integer teamSupervisor;

    @JsonProperty("in_appointment_date")
    @JsonAlias("appointmentDate")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String appointmentDate;

    @JsonProperty("in_agent_group_id")
    @JsonAlias("agentGroupId")
    private Integer agentGroupId;

    @JsonProperty("in_min_skill_level")
    @JsonAlias("minSkillLevel")
    private Integer minSkillLevel;

    @JsonProperty("in_agent_skill_level")
    @JsonAlias("agentSkillLevel")
    private Integer agentSkillLevel;

    @JsonProperty("in_first_call_click")
    @JsonAlias("firstCallClick")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String firstCallClick;

    @JsonProperty("in_agent_hold")
    @JsonAlias("agentHold")
    private Integer agentHold;

    @JsonProperty("in_assigned_group_id")
    @JsonAlias("assignedGroupId")
    private Integer assignedGroupId;

    @JsonProperty("in_bdr_action_type")
    @JsonAlias("bdrActionType")
    private String bdrActionType;

    @JsonProperty("in_priority")
    @JsonAlias("priority")
    private Integer priority;

    @JsonProperty("in_reference_lead_id")
    @JsonAlias("referenceLeadId")
    private Integer referenceLeadId;

    @JsonProperty("in_delivery_package_code")
    @JsonAlias("deliveryPackageCode")
    private String deliveryPackageCode;

    @JsonProperty("in_ip_address")
    @JsonAlias("ipAddress")
    private String ipAddress;

    @JsonProperty("in_ip_country")
    @JsonAlias("ipCountry")
    private String ipCountry;

    @JsonProperty("in_ip_region")
    @JsonAlias("ipRegion")
    private String ipRegion;

    @JsonProperty("in_ip_city")
    @JsonAlias("ipCity")
    private String ipCity;

    @JsonProperty("in_reference_org_id")
    @JsonAlias("referenceOrgId")
    private Integer referenceOrgId;

    @JsonProperty("in_batch_id")
    @JsonAlias("batchId")
    private String batchId;

    @JsonProperty("in_lead_sequence")
    @JsonAlias("leadSequence")
    private Integer leadSequence;

    @JsonProperty("in_batch_sequence")
    @JsonAlias("batchSequence")
    private Integer batchSequence;

    @JsonProperty("in_fresh_lead_id")
    @JsonAlias("freshLeadId")
    private Integer freshLeadId;

    @JsonProperty("in_fresh_org_id")
    @JsonAlias("freshOrgId")
    private Integer freshOrgId;

    @JsonProperty("in_postback_status")
    @JsonAlias("postbackStatus")
    private String postbackStatus;

    @JsonProperty("in_postback_date")
    @JsonAlias("postbackDate")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String postbackDate;

    @JsonProperty("in_validator_user_id")
    @JsonAlias("validatorUserId")
    private Integer validatorUserId;

    @JsonProperty("in_revised_lead_name")
    @JsonAlias("revisedLeadName")
    private String revisedLeadName;

    @JsonProperty("in_is_reassigned_validator")
    @JsonAlias("isReassignedValidator")
    private Boolean isReassignedValidator;

    @JsonProperty("in_payment_transaction_id")
    @JsonAlias("paymentTransactionId")
    private String paymentTransactionId;
}
