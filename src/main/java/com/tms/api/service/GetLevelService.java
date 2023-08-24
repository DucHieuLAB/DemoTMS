package com.tms.api.service;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;

import java.util.Set;

public interface GetLevelService {
   ResponseData<Set<ResponseDataDto>> getProvince(Integer level);

   ResponseData<Set<ResponseDataDto>> getDistrict(Integer provinceId, Integer level);

   ResponseData<Set<ResponseDataDto>> getSubDistrict(Integer districtId, Integer level);
}
