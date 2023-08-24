package com.tms.api.dto.RawData;

import com.tms.api.dto.information.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubDistrictRawData {
    private SubDistrictInfo subDistrict;
    private DistrictInfo district;
    private ProvinceInfo province;
    private FFMInfo ffm;
    private LMInfo lm;

    public SubDistrictRawData(Integer subdtId, String subdtName, Integer dtId, String dtName, Integer prvId, String prvName,
                              Integer ffmId, String ffmName, String ffmShortName, Integer lmId, String lmName, String lmShortName) {
        this.subDistrict = new SubDistrictInfo();
        this.district = new DistrictInfo();
        this.province = new ProvinceInfo();
        this.ffm = new FFMInfo();
        this.lm = new LMInfo();

        this.subDistrict.setSubdtId(subdtId);
        this.subDistrict.setName(subdtName);
        this.district.setDtId(dtId);
        this.district.setName(dtName);
        this.province.setPrvId(prvId);
        this.province.setName(prvName);
        this.ffm.setFfmId(ffmId);
        this.ffm.setName(ffmName);
        this.ffm.setShortName(ffmShortName);
        this.lm.setLmId(lmId);
        this.lm.setName(lmName);
        this.lm.setShortName(lmShortName);
    }
    public SubDistrictRawData(Integer subdtId, String subdtName, Integer dtId, String dtName,
                              Integer ffmId, String ffmName, String ffmShortName, Integer lmId, String lmName, String lmShortName) {
        this.subDistrict = new SubDistrictInfo();
        this.district = new DistrictInfo();
        this.ffm = new FFMInfo();
        this.lm = new LMInfo();

        this.subDistrict.setSubdtId(subdtId);
        this.subDistrict.setName(subdtName);
        this.district.setDtId(dtId);
        this.district.setName(dtName);
        this.ffm.setFfmId(ffmId);
        this.ffm.setName(ffmName);
        this.ffm.setShortName(ffmShortName);
        this.lm.setLmId(lmId);
        this.lm.setName(lmName);
        this.lm.setShortName(lmShortName);
    }
    public SubDistrictRawData(Integer subdtId, String subdtName, Integer ffmId, String ffmName, String ffmShortName,
                              Integer lmId, String lmName, String lmShortName) {
        this.subDistrict = new SubDistrictInfo();
        this.ffm = new FFMInfo();
        this.lm = new LMInfo();

        this.subDistrict.setSubdtId(subdtId);
        this.subDistrict.setName(subdtName);
        this.ffm.setFfmId(ffmId);
        this.ffm.setName(ffmName);
        this.ffm.setShortName(ffmShortName);
        this.lm.setLmId(lmId);
        this.lm.setName(lmName);
        this.lm.setShortName(lmShortName);
    }
}
