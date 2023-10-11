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
public class GetLeadfor {
    private Integer agentId;
    private Integer addLimit;
    private Integer maxDayCall;
    private Integer maxTotalCall;
}
