package com.tms.dto.request.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetScheduleUpdate {
    @Pattern(regexp = DaoConst.ID_LIST_SPLIT_BY_COMMA_REGEX, message = "{ids.Pattern.message}")
    private String ids;
    private Integer locationId;
    private Integer locationLevel;
    private Integer fulfillmentId;
    private Integer warehouseId;
    private Integer lastmileId;
    @Pattern(regexp = DaoConst.DATE_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String updateDay;
    private Integer createdBy;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String createdDate;
    private Integer modifiedBy;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String modifiedDate;
    private Integer limit;
    private Integer offset;
}
