package com.tms.api.dto;

import com.tms.api.dto.information.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SubDistrictDto {
    private SubDistrictInfo subdtInfo;
    private ProvinceInfo provinceInfo;
    private DistrictInfo districtInfo;
    private Set<FFMInfo> ffmInfos;
    private Set<LMInfo> lmInfos;
}
