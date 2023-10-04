package com.tms.dto.request.ClFreshGetLead;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.tms.dto.request.clCallback.InsClCallback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetLeadStatus {
    private SetLeadFresh setLeadFresh;
    private InsClCallback insClCallback;
    private SoSaleOderInsert soSaleOderInsert;

}
// private Integer leadId;
    // private Integer agenId;
    // private Integer leadStatus;
    // private String fcrReason;
    // private String address;
    // private String phone;
    // private Integer prodId;
    // private String prodName;
    // private Integer paymentMethod;
    // private String comment;
    // @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    // private String requestTime;
    // private Integer orgId;
    // private String name;
    // private SetLeadFresh setLeadFresh;
    // private SetLeadStatusCallback setLeadStatusCallback;
    // private SoSaleOderInsert soSaleOderInsert;