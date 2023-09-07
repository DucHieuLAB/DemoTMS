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
public class SoSaleOderInsert {
    private Integer orgId;
    private Integer leadId;
    private String leadName;
    private String leadPhone;
    private Integer paymentMethod;
}
