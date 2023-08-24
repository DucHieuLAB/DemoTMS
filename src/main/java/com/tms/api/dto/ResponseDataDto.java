package com.tms.api.dto;

import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataDto {

    private Integer locationId;
    private String locationName;
    Set<FFMInfo> ffms;
    Set<LMInfo> lms;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResponseDataDto) {
            ResponseDataDto another = (ResponseDataDto) obj;
            if (this.locationId.equals(another.getLocationId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 218 + this.locationId.hashCode();
    }
}
