package com.tms.dto.request.clFresh;

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
    private Integer leadId;
    @JsonProperty("in_agc_id")
    private Integer agcId;
    @JsonProperty("in_agc_code")
    private String agcCode;
    @JsonProperty("in_org_id")
    private Integer orgId;
    @JsonProperty("in_cc_code")
    private String ccCode;
    @JsonProperty("in_name")
    private String name;
    @JsonProperty("in_phone")
    private String phone;
    @JsonProperty("in_prod_id")
    private Integer prodId;
    @JsonProperty("in_prod_name")
    private String prodName;
    @JsonProperty("in_assigned")
    private Integer assigned;
    @JsonProperty("in_called_by")
    private Integer calledBy;
    @JsonProperty("in_address")
    private String address;
    @JsonProperty("in_province")
    private String province;
    @JsonProperty("in_district")
    private String district;
    @JsonProperty("in_subdistrict")
    private String subdistrict;
    @JsonProperty("in_comment")
    private String comment;
    @JsonProperty("in_last_call_status")
    private Integer lastCallStatus;
    @JsonProperty("in_day_call")
    private Integer dayCall;
    @JsonProperty("in_total_call")
    private Integer totalCall;
    @JsonProperty("in_amount")
    private String amount;
    @JsonProperty("in_lead_status")
    private Integer leadStatus;
    @JsonProperty("in_result")
    private Integer result;
    @JsonProperty("in_userdefin01")
    private String userDefin01;
    @JsonProperty("in_userdefin02")
    private String userDefin02;
    @JsonProperty("in_userdefin03")
    private String userDefin03;
    @JsonProperty("in_userdefin04")
    private String userDefin04;
    @JsonProperty("in_userdefin05")
    private String userDefin05;
    @JsonProperty("in_attribute")
    private JsonNode attribute;
    @JsonProperty("in_create_date")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String createDate;
    @JsonProperty("in_modify_date")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String modifyDate;
    @JsonProperty("in_modify_by")
    private Integer modifyBy;
    @JsonProperty("in_cp_id")
    private Integer cpId;
    @JsonProperty("in_callinglist_id")
    private Integer callingListId;
    @JsonProperty("in_lead_type")
    private String leadType;
    @JsonProperty("in_agc_lead_address")
    private String agcLeadAddress;
    @JsonProperty("in_other_name1")
    private String otherName1;
    @JsonProperty("in_other_phone1")
    private String otherPhone1;
    @JsonProperty("in_other_name2")
    private String otherName2;
    @JsonProperty("in_other_phone2")
    private String otherPhone2;
    @JsonProperty("in_other_name3")
    private String otherName3;
    @JsonProperty("in_other_phone3")
    private String otherPhone3;
    @JsonProperty("in_other_name4")
    private String otherName4;
    @JsonProperty("in_other_phone4")
    private String otherPhone4;
    @JsonProperty("in_other_name5")
    private String otherName5;
    @JsonProperty("in_other_phone5")
    private String otherPhone5;
    @JsonProperty("in_last_call_time")
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String lastCallTime;
    @JsonProperty("in_next_call_time")
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-time.Pattern.message}")
    private String nextCallTime;
    @JsonProperty("in_number_of_day")
    private Integer numberOfDay;
    @JsonProperty("in_attempt_busy")
    private Integer attemptBusy;
    @JsonProperty("in_attempt_noans")
    private Integer attemptNoans;
    @JsonProperty("in_attempt_unreachable")
    private Integer attemptUnreachable;
    @JsonProperty("in_attempt_other1")
    private Integer attemptOther1;
    @JsonProperty("in_attempt_other2")
    private Integer attemptOther2;
    @JsonProperty("in_attempt_other3")
    private Integer attemptOther3;
    @JsonProperty("in_click_id")
    private String clickId;
    @JsonProperty("in_affiliate_id")
    private String affiliateId;
    @JsonProperty("in_subid1")
    private String subid1;
    @JsonProperty("in_subid2")
    private String subid2;
    @JsonProperty("in_subid3")
    private String subid3;
    @JsonProperty("in_subid4")
    private String subid4;
    @JsonProperty("in_subid5")
    private String subid5;
    @JsonProperty("in_networkid")
    private String networkId;
    @JsonProperty("in_pid")
    private String pid;
    @JsonProperty("in_tracking_url_id")
    private String trackingUrlId;
    @JsonProperty("in_offer_id")
    private String offerId;
    @JsonProperty("in_agc_offer_id")
    private String agcOfferId;
    @JsonProperty("in_terms")
    private String terms;
    @JsonProperty("in_price")
    private String price;
    @JsonProperty("in_unit")
    private String unit;
    @JsonProperty("in_customer_age")
    private Integer customerAge;
    @JsonProperty("in_customer_email")
    private String customerEmail;
    @JsonProperty("in_customer_comment")
    private String customerComment;
    @JsonProperty("in_internal_comment")
    private String internalComment;
    @JsonProperty("in_carrier_comment")
    private String carrierComment;
    @JsonProperty("in_cl_group")
    private Integer clGroup;
    @JsonProperty("in_agcoffer_id")
    private String agcofferId;
    @JsonProperty("in_assigned_name")
    private String assignedName;
    @JsonProperty("in_calledby_name")
    private String calledByName;
    @JsonProperty("in_callinglist_name")
    private String callingListName;
    @JsonProperty("in_campaign_name")
    private String campaignName;
    @JsonProperty("in_lastcall_status_name")
    private String lastCallStatusName;
    @JsonProperty("in_lead_status_name")
    private String leadStatusName;
    @JsonProperty("in_source")
    private String source;
    @JsonProperty("in_userdefin_03")
    private String userDefin_03;
    @JsonProperty("in_neighborhood")
    private String neighborhood;
    @JsonProperty("in_postal_code")
    private String postalCode;
    @JsonProperty("in_tracker_id")
    private Integer trackerId;
    @JsonProperty("in_agent_note")
    private String agentNote;
    @JsonProperty("in_actual_call")
    private Integer actualCall;
    @JsonProperty("in_first_call_time")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String firstCallTime;
    @JsonProperty("in_first_call_by")
    private Integer firstCallBy;
    @JsonProperty("in_first_call_status")
    private Integer firstCallStatus;
    @JsonProperty("in_first_call_reason")
    private String firstCallReason;
    @JsonProperty("in_first_call_comment")
    private String firstCallComment;
    @JsonProperty("in_fcr_time")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String fcrTime;
    @JsonProperty("in_fcr_by")
    private Integer fcrBy;
    @JsonProperty("in_fcr_status")
    private Integer fcrStatus;
    @JsonProperty("in_fcr_reason")
    private String fcrReason;
    @JsonProperty("in_fcr_comment")
    private String fcrComment;
    @JsonProperty("in_crm_action_type")
    private Integer crmActionType;
    @JsonProperty("in_team")
    private Integer team;
    @JsonProperty("in_team_supervisor")
    private Integer teamSupervisor;
    @JsonProperty("in_appointment_date")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String appointmentDate;
    @JsonProperty("in_agent_group_id")
    private Integer agentGroupId;
    @JsonProperty("in_min_skill_level")
    private Integer minSkillLevel;
    @JsonProperty("in_agent_skill_level")
    private Integer agentSkillLevel;
    @JsonProperty("in_first_call_click")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String firstCallClick;
    @JsonProperty("in_agent_hold")
    private Integer agentHold;
    @JsonProperty("in_assigned_group_id")
    private Integer assignedGroupId;
    @JsonProperty("in_bdr_action_type")
    private String bdrActionType;
    @JsonProperty("in_priority")
    private Integer priority;
    @JsonProperty("in_reference_lead_id")
    private Integer referenceLeadId;
    @JsonProperty("in_delivery_package_code")
    private String deliveryPackageCode;
    @JsonProperty("in_ip_address")
    private String ipAddress;
    @JsonProperty("in_ip_country")
    private String ipCountry;
    @JsonProperty("in_ip_region")
    private String ipRegion;
    @JsonProperty("in_ip_city")
    private String ipCity;
    @JsonProperty("in_reference_org_id")
    private Integer referenceOrgId;
    @JsonProperty("in_batch_id")
    private String batchId;
    @JsonProperty("in_lead_sequence")
    private Integer leadSequence;
    @JsonProperty("in_batch_sequence")
    private Integer batchSequence;
    @JsonProperty("in_fresh_lead_id")
    private Integer freshLeadId;
    @JsonProperty("in_fresh_org_id")
    private Integer freshOrgId;
    @JsonProperty("in_postback_status")
    private String postbackStatus;
    @JsonProperty("in_postback_date")
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String postbackDate;
    @JsonProperty("in_validator_user_id")
    private Integer validatorUserId;
    @JsonProperty("in_revised_lead_name")
    private String revisedLeadName;
    @JsonProperty("in_is_reassigned_validator")
    private Boolean isReassignedValidator;
    @JsonProperty("in_payment_transaction_id")
    private String paymentTransactionId;
}
