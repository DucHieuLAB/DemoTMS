package com.tms.dto.request.ClFreshGetLead;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateforagentHold {
      private Integer LeadID;
      private Integer agentId;
      
}
