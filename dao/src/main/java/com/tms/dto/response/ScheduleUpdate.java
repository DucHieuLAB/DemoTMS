package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleUpdate implements Serializable {
    private Integer id;
    private Integer locationId;
    private Integer locationLevel;
    private Integer fulfillmentId;
    private Integer warehouseId;
    private Integer lastmileId;
    private LocalDate updateDay;
    private Boolean isDeleted;
    private Integer createdBy;
    private LocalDateTime createdDate;
    private Integer modifiedBy;
    private LocalDateTime modifiedDate;
}
