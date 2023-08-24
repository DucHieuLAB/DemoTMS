package com.tms.dto.request.schedule;

import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class UpdScheduleUpdate implements Serializable {
    @NotNull
    private Integer id;
    private Integer locationId;
    private Integer locationLevel;
    @NotNull
    private Integer fulfillmentId;
    @NotNull
    private Integer warehouseId;
    @NotNull
    private Integer lastmileId;
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String updateDay;
    @NotNull
    private Boolean isDeleted;
    private Integer modifiedBy;
}
