package com.tms.dto.request.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLeadToFillter {

    private String in_attribute3;
}
