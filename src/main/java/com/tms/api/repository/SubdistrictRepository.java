package com.tms.api.repository;

import com.tms.api.dto.RawData.SubDistrictRawData;
import com.tms.api.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubdistrictRepository extends JpaRepository<SubDistrict, Integer> {
    @Query(" select distinct new com.tms.api.dto.RawData.SubDistrictRawData(sdt.id, sdt.name,dt.id, dt.name, prv.id, prv.name," +
            " ffm.id, ffm.name, ffm.shortName,lm.id, lm.name, lm.shortName )" +
            " from DefaultDelivery df " +
            " left join Province prv on prv.id= df.provinceId " +
            " left join District dt on dt.prvId= prv.id " +
            " left join SubDistrict sdt on sdt.dtId = dt.id " +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastMileId " +
            " where dt.id = ?1" +
            " order by sdt.id ")
    List<SubDistrictRawData> getSubDistrictByLevelProvince(Integer districtId);

    @Query(" select distinct new com.tms.api.dto.RawData.SubDistrictRawData(sdt.id, sdt.name,dt.id, dt.name, ffm.id, " +
            "ffm.name, ffm.shortName,lm.id, lm.name, lm.shortName )" +
            "from DefaultDelivery df " +
            " left join District dt on dt.id= df.provinceId" +
            " left join SubDistrict sdt on sdt.dtId = dt.id " +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastMileId " +
            "where dt.id = ?1 " +
            " order by sdt.id ")
    List<SubDistrictRawData> getSubDistrictByLevelDistrict(Integer districtId);

    @Query(" select distinct new com.tms.api.dto.RawData.SubDistrictRawData(sdt.id, sdt.name, ffm.id, ffm.name, ffm.shortName," +
            " lm.id, lm.name, lm.shortName )" +
            " from DefaultDelivery df " +
            " left join SubDistrict sdt on df.provinceId = sdt.id " +
            " left join Partner ffm on ffm.id = df.partnerId " +
            " left join Partner lm on lm.id = df.lastMileId " +
            " left join District dt on dt.id= df.provinceId" +
            " where dt.id = ?1" +
            " order by sdt.id ")
    List<SubDistrictRawData> getSubDistrictByLevelSubDistrict(Integer districtId);
}
