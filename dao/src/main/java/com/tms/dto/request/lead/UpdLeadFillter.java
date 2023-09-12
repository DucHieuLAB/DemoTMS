package com.tms.dto.request.lead;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tms.DaoConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdLeadFillter {
    @NotNull
    private Integer[] Leadids;
    private String name;
    private String phone;
    private Integer prodId;
    private String comment;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message }")
    private String createdate;
    @NotNull
    private Integer status;
    @NotNull
    String attribute3;
    private Integer modifiedBy;
}
