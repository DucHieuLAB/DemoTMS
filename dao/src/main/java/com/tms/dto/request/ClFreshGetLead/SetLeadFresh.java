package com.tms.dto.request.ClFreshGetLead;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetLeadFresh {
    private Integer leadId;
    private Integer agenId;
    private Integer leadStatus;
    private String fcrReason;
    private String address;
    private String phone;
    private Integer prodId;
    private String prodName;
    private String comment;
}
