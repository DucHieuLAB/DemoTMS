package com.tms.dto.request.clBasket;

import com.tms.DaoConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GetLeadBasketsInTimeRange {
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String startTime;
    @Pattern(regexp = DaoConst.DATE_TIME_BETWEEN_REGEX, message = "{date-between.Pattern.message}")
    private String endTime;

    @Override
    public String toString() {
        return "GetLeadBasketsInTimeRange{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
