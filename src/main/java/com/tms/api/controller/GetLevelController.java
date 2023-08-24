package com.tms.api.controller;

import com.tms.api.dto.ResponseDataDto;
import com.tms.api.response.ResponseData;
import com.tms.api.service.GetLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/location")
@Validated
public class GetLevelController {
    @Autowired
    GetLevelService getLevelService;

    @GetMapping("/getProvince")
    public ResponseData<Set<ResponseDataDto>> getProvince(@RequestParam Integer level) {
        return getLevelService.getProvince(level);
    }

    @GetMapping("/getDistrict")
    public ResponseData<Set<ResponseDataDto>> getDistrict(@RequestParam Integer provinceId, @RequestParam Integer level) {
        return getLevelService.getDistrict(provinceId, level);
    }

    @GetMapping("/getSubDistrict")
    public ResponseData<Set<ResponseDataDto>> getSubDistrict(@RequestParam Integer districtId, @RequestParam Integer level) {
        return getLevelService.getSubDistrict(districtId, level);
    }
}
