package com.tms.dto.request.ClFreshGetLead;

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
public class SetLeadStatusCallback {
    private Integer leadId;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date.Pattern.message}")
    private String requestTime;
    private Integer orgId;
    
}
