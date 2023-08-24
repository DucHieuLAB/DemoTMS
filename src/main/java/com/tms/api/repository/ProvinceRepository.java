package com.tms.api.repository;

import com.tms.api.dto.RawData.ProvinceRawData;
import com.tms.api.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query("select distinct new com.tms.api.dto.RawData.ProvinceRawData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName," +
            " lm.id, lm.name, lm.shortName )" +
            "from DefaultDelivery df " +
            "left join Province prv on prv.id= df.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            "order by prv.id ")
    List<ProvinceRawData> getProvinceByLevelProvince();

    @Query("select distinct new com.tms.api.dto.RawData.ProvinceRawData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName," +
            " lm.id, lm.name, lm.shortName )" +
            "from DefaultDelivery df " +
            "left join District dt on df.provinceId = dt.id " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            "left join Province prv on dt.prvId = prv.id " +
            "order by prv.id")
    List<ProvinceRawData> getProvinceByLevelDistrict();

    @Query("select distinct new com.tms.api.dto.RawData.ProvinceRawData(prv.id, prv.name, ffm.id, ffm.name, ffm.shortName, " +
            "lm.id, lm.name, lm.shortName  ) " +
            "from DefaultDelivery df " +
            "left join SubDistrict sdt on df.provinceId = sdt.id " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            "left join District dt on sdt.dtId = dt.id " +
            "left join Province prv on dt.prvId = prv.id " +
            "order by prv.id")
    List<ProvinceRawData> getProvinceByLevelSubDistrict();
}
