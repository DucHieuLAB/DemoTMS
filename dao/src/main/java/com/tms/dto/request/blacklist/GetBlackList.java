package com.tms.dto.request.blacklist;

import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetBlackList {
    private Integer blId;
    private Integer orgId;
    private Integer leadId;
    private String leadName;
    private String phone;
    private Integer prodId;
    private String comment;
    private Integer modifyby;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String modifydate;
    private Integer limit;
    private Integer offset;
}
