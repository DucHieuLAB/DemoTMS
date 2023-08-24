package com.tms.api.dto.information;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LMInfo {
    private Integer lmId;
    private String name;
    private String shortName;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LMInfo) {
            LMInfo another = (LMInfo) obj;
            if (this.lmId.equals(another.getLmId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 218 + this.lmId.hashCode();
    }
}
