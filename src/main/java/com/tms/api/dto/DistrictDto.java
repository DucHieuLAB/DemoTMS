package com.tms.api.dto;

import com.tms.api.dto.RawData.DistrictRawData;
import com.tms.api.dto.information.DistrictInfo;
import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.ProvinceInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DistrictDto {
    private DistrictInfo districtInfo;
    private ProvinceInfo provinceInfo;
    private Set<FFMInfo> ffmInfos;
    private Set<LMInfo> lmInfos;

    public DistrictDto(DistrictRawData data) {
        if (data != null) {
            this.provinceInfo = data.getProvinceInfo();
            this.districtInfo = data.getDistrictInfo();
            this.ffmInfos = new HashSet<>();
            this.ffmInfos.add(data.getFfmInfo());
            this.lmInfos = new HashSet<>();
            this.lmInfos.add(data.getLmInfo());
        }
    }
}
