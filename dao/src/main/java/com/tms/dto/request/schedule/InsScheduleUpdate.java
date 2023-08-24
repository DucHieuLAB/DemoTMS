package com.tms.dto.request.schedule;

import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class InsScheduleUpdate implements Serializable {
    @NotNull
    private Integer locationId;
    @NotNull
    private Integer locationLevel;
    @NotNull
    private Integer fulfillmentId;
    @NotNull
    private Integer warehouseId;
    @NotNull
    private Integer lastmileId;
    @NotBlank
    @Pattern(regexp = DaoConst.DATE_REGEX, message = "{date.Pattern.message}")
    private String updateDay;
    private Integer createdBy;
}
