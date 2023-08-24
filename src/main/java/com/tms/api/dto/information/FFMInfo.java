package com.tms.api.dto.information;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FFMInfo {
    private Integer ffmId;
    private String name;
    private String shortName;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FFMInfo) {
            FFMInfo another = (FFMInfo) obj;
            if (this.ffmId.equals(another.getFfmId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 812 + this.ffmId.hashCode();
    }
}
