package com.tms.api.dto.RawData;

import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.dto.information.ProvinceInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProvinceRawData {
    private ProvinceInfo provinceInfo;
    private FFMInfo ffmInfo;
    private LMInfo lmInfo;


    public ProvinceRawData(Integer prvId, String prvName, Integer ffmId, String ffmName, String ffmShortName,
                           Integer lmId, String lmName, String lmShortName) {
        this.provinceInfo = new ProvinceInfo();
        this.ffmInfo = new FFMInfo();
        this.lmInfo = new LMInfo();

        this.provinceInfo.setPrvId(prvId);
        this.provinceInfo.setName(prvName);
        this.ffmInfo.setFfmId(ffmId);
        this.ffmInfo.setName(ffmName);
        this.ffmInfo.setShortName(ffmShortName);
        this.lmInfo.setLmId(lmId);
        this.lmInfo.setName(lmName);
        this.lmInfo.setShortName(lmShortName);
    }

}
