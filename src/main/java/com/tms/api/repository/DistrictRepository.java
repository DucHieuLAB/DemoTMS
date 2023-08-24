package com.tms.api.repository;

import com.tms.api.dto.RawData.DistrictRawData;
import com.tms.api.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("select distinct new com.tms.api.dto.RawData.DistrictRawData(dt.id, dt.name,prv.id, prv.name, ffm.id, ffm.name," +
            " ffm.shortName, lm.id, lm.name, lm.shortName) " +
            "from DefaultDelivery df " +
            "left join Province prv on prv.id = df.provinceId " +
            "left join District dt  on dt.prvId = prv.id " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            " where prv.id= ?1 " +
            "order by dt.id")
    List<DistrictRawData> getDistinctByLevelProvince(Integer provinceId);

    @Query("select distinct new com.tms.api.dto.RawData.DistrictRawData(dt.id, dt.name,ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName )" +
            "from DefaultDelivery df " +
            "left join District dt on dt.id = df.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            "left join Province prv on prv.id = df.provinceId " +
            "where prv.id= ?1 " +
            "order by dt.id ")
    List<DistrictRawData> getDistinctByLevelDistrict(Integer provinceId);

    @Query("select distinct new com.tms.api.dto.RawData.DistrictRawData(dt.id, dt.name,ffm.id, ffm.name, ffm.shortName," +
            "lm.id, lm.name, lm.shortName) " +
            "from DefaultDelivery df " +
            "left join SubDistrict sdt on sdt.id = df.provinceId " +
            "left join Partner ffm on ffm.id = df.partnerId " +
            "left join Partner lm on lm.id = df.lastMileId " +
            "left join District dt on dt.id =sdt.dtId  " +
            "where dt.prvId = ?1 " +
            "order by dt.id ")
    List<DistrictRawData> getDistinctByLevelSubDistrict(Integer provinceId);
}
