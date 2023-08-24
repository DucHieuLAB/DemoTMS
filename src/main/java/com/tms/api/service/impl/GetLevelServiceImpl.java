package com.tms.api.service.impl;

import com.tms.api.dto.RawData.DistrictRawData;
import com.tms.api.dto.RawData.ProvinceRawData;
import com.tms.api.dto.RawData.SubDistrictRawData;
import com.tms.api.dto.ResponseDataDto;
import com.tms.api.dto.information.FFMInfo;
import com.tms.api.dto.information.LMInfo;
import com.tms.api.repository.DistrictRepository;
import com.tms.api.repository.ProvinceRepository;
import com.tms.api.repository.SubdistrictRepository;
import com.tms.api.response.ResponseData;
import com.tms.api.service.BaseService;
import com.tms.api.service.GetLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GetLevelServiceImpl extends BaseService implements GetLevelService {

    @Autowired
    ProvinceRepository provinceRepo;
    @Autowired
    SubdistrictRepository subDistrictRepo;
    @Autowired
    DistrictRepository districtRepo;

    Set<ResponseDataDto> list;
    Set<ResponseDataDto> result = new HashSet<>();
    @Override
    public ResponseData<Set<ResponseDataDto>> getProvince(Integer level) {
        List<ProvinceRawData> provinceList;
        switch (level) {
            case 1:
                provinceList = provinceRepo.getProvinceByLevelProvince();
                list = convertData(provinceList);
                return new ResponseData<>("success", list);
            case 2:
                provinceList = provinceRepo.getProvinceByLevelDistrict();
                list = convertData(provinceList);
                return new ResponseData<>("success", list);
            case 3:
                provinceList = provinceRepo.getProvinceByLevelSubDistrict();
                list = convertData(provinceList);
                return new ResponseData<>("success", list);

        }
        return new ResponseData<>("please check validated or level");
    }

    private Set<ResponseDataDto> convertData(List<ProvinceRawData> dtos) {

        for (ProvinceRawData dto : dtos) {
            if (dto.getProvinceInfo() == null || dto.getProvinceInfo().getPrvId() == null) {
                continue;
            }
            ResponseDataDto dataOutput = new ResponseDataDto();
            dataOutput.setLocationId(dto.getProvinceInfo().getPrvId());
            dataOutput.setLocationName(dto.getProvinceInfo().getName());

            Set<FFMInfo> listFfm = new HashSet<>();
            dtos.forEach(dto1 -> {
                if (dto1.getProvinceInfo() != null
                        && dto.getProvinceInfo() != null
                        && dto.getProvinceInfo().getPrvId() != null
                        && dto.getProvinceInfo().getPrvId().equals(dto1.getProvinceInfo().getPrvId())
                        && dto1.getFfmInfo() != null) {
                    FFMInfo ffm = new FFMInfo();
                    ffm.setFfmId(dto1.getFfmInfo().getFfmId());
                    ffm.setName(dto1.getFfmInfo().getName());
                    ffm.setShortName(dto1.getFfmInfo().getShortName());
                    listFfm.add(ffm);
                }
            });

            Set<LMInfo> listLm = new HashSet<>();
            dtos.forEach(dto2 -> {
                if (dto2.getProvinceInfo() != null
                        && dto.getProvinceInfo() != null
                        && dto.getProvinceInfo().getPrvId() != null
                        && dto.getProvinceInfo().getPrvId().equals(dto2.getProvinceInfo().getPrvId())
                        && dto2.getLmInfo() != null) {
                    LMInfo lm = new LMInfo();
                    lm.setLmId(dto2.getLmInfo().getLmId());
                    lm.setName(dto2.getLmInfo().getName());
                    lm.setShortName(dto2.getLmInfo().getShortName());
                    listLm.add(lm);
                }
            });
            dataOutput.setFfms(listFfm);
            dataOutput.setLms(listLm);
            result.add(dataOutput);
        }

        return result;
    }


    @Override
    public ResponseData<Set<ResponseDataDto>> getDistrict(Integer provinceId, Integer level) {
        List<DistrictRawData> districtList;

        switch (level) {
            case 1:
                districtList = districtRepo.getDistinctByLevelProvince(provinceId);
                list = convertDistrict(districtList);
                return new ResponseData<>("success", list);
            case 2:
                districtList = districtRepo.getDistinctByLevelDistrict(provinceId);
                list = convertDistrict(districtList);
                return new ResponseData<>("success", list);
            case 3:
                districtList = districtRepo.getDistinctByLevelSubDistrict(provinceId);
                list = convertDistrict(districtList);
                return new ResponseData<>("success", list);
        }
        return new ResponseData<>("please check validated or level");
    }

    private Set<ResponseDataDto> convertDistrict(List<DistrictRawData> districDto) {

        for (DistrictRawData rawData : districDto) {
            if (rawData.getDistrictInfo() == null || rawData.getDistrictInfo().getDtId() == null) {
                continue;
            }
            ResponseDataDto dataOutput2 = new ResponseDataDto();
            dataOutput2.setLocationId(rawData.getDistrictInfo().getDtId());
            dataOutput2.setLocationName(rawData.getDistrictInfo().getName());

            Set<FFMInfo> listFfm = new HashSet<>();
            districDto.forEach(dto1 -> {
                if (dto1.getDistrictInfo() != null
                        && rawData.getDistrictInfo() != null
                        && rawData.getDistrictInfo().getDtId() != null
                        && rawData.getDistrictInfo().getDtId().equals(dto1.getDistrictInfo().getDtId())
                        && dto1.getFfmInfo() != null) {
                    FFMInfo ffm = new FFMInfo();
                    ffm.setFfmId(dto1.getFfmInfo().getFfmId());
                    ffm.setName(dto1.getFfmInfo().getName());
                    ffm.setShortName(dto1.getFfmInfo().getShortName());
                    listFfm.add(ffm);
                }
            });
            Set<LMInfo> listLm = new HashSet<>();
            districDto.forEach(dto2 -> {
                if (dto2.getDistrictInfo() != null
                        && rawData.getDistrictInfo() != null
                        && rawData.getDistrictInfo().getDtId() != null
                        && rawData.getDistrictInfo().getDtId().equals(dto2.getDistrictInfo().getDtId())
                        && dto2.getLmInfo() != null) {
                    LMInfo lm = new LMInfo();
                    lm.setLmId(dto2.getLmInfo().getLmId());
                    lm.setName(dto2.getLmInfo().getName());
                    lm.setShortName(dto2.getLmInfo().getShortName());
                    listLm.add(lm);
                }
            });
            dataOutput2.setFfms(listFfm);
            dataOutput2.setLms(listLm);
            result.add(dataOutput2);
        }
        return result;
    }

    @Override
    public ResponseData<Set<ResponseDataDto>> getSubDistrict(Integer districtId, Integer level) {
        List<SubDistrictRawData> subDistrictList;

        switch (level) {
            case 1:
                subDistrictList = subDistrictRepo.getSubDistrictByLevelProvince(districtId);
                list = convertSubDistrict(subDistrictList);
                return new ResponseData<>("success", list);
            case 2:
                subDistrictList = subDistrictRepo.getSubDistrictByLevelDistrict(districtId);
                list = convertSubDistrict(subDistrictList);
                return new ResponseData<>("success", list);
            case 3:
                subDistrictList = subDistrictRepo.getSubDistrictByLevelSubDistrict(districtId);
                list = convertSubDistrict(subDistrictList);
                return new ResponseData<>("success", list);
        }
        return new ResponseData<>("please check validated or level");
    }

    private Set<ResponseDataDto> convertSubDistrict(List<SubDistrictRawData> subDistrictDto) {

        for (SubDistrictRawData rawData : subDistrictDto) {
            if (rawData.getSubDistrict() == null || rawData.getSubDistrict().getSubdtId() == null) {
                continue;
            }
            ResponseDataDto dataOutput3 = new ResponseDataDto();
            dataOutput3.setLocationId(rawData.getSubDistrict().getSubdtId());
            dataOutput3.setLocationName(rawData.getSubDistrict().getName());
            Set<FFMInfo> listFfm = new HashSet<>();
            subDistrictDto.forEach(dto1 -> {
                if (dto1.getSubDistrict() != null
                        && rawData.getSubDistrict() != null
                        && rawData.getSubDistrict().getSubdtId() != null
                        && rawData.getSubDistrict().getSubdtId().equals(dto1.getSubDistrict().getSubdtId())
                        && dto1.getFfm() != null) {
                    FFMInfo ffm = new FFMInfo();
                    ffm.setFfmId(dto1.getFfm().getFfmId());
                    ffm.setName(dto1.getFfm().getName());
                    ffm.setShortName(dto1.getFfm().getShortName());
                    listFfm.add(ffm);
                }
            });
            Set<LMInfo> listLm = new HashSet<>();
            subDistrictDto.forEach(dto2 -> {
                if (dto2.getSubDistrict() != null
                        && rawData.getSubDistrict() != null
                        && rawData.getSubDistrict().getSubdtId() != null
                        && rawData.getSubDistrict().getSubdtId().equals(dto2.getSubDistrict().getSubdtId())
                        && dto2.getLm() != null) {
                    LMInfo lm = new LMInfo();
                    lm.setLmId(dto2.getLm().getLmId());
                    lm.setName(dto2.getLm().getName());
                    lm.setShortName(dto2.getLm().getShortName());
                    listLm.add(lm);
                }
            });
            dataOutput3.setFfms(listFfm);
            dataOutput3.setLms(listLm);
            result.add(dataOutput3);
        }
        return result;
    }

}
