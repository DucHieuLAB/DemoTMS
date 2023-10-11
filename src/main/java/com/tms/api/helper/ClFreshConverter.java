package com.tms.api.helper;

import com.tms.dto.request.clFresh.InsClFresh;
import com.tms.dto.response.ClBasket;

import java.util.ArrayList;
import java.util.List;

public class ClFreshConverter {
    public static List<InsClFresh> convertToInsClFreshs(List<ClBasket> clBasketList) {
        List<InsClFresh> insClFreshList = new ArrayList<>();
        for (ClBasket clBasket : clBasketList) {
            InsClFresh insClFresh = new InsClFresh();
            insClFresh.setLeadId(clBasket.getLeadId());
            insClFresh.setAgcId(clBasket.getAgcId());
            insClFresh.setAgcCode(clBasket.getAgcCode());
            insClFresh.setOrgId(clBasket.getOrgId());
            insClFresh.setCcCode(clBasket.getCcCode());
            insClFresh.setName(clBasket.getName());
            insClFresh.setPhone(clBasket.getPhone());
            insClFresh.setProdId(clBasket.getProdId());
            insClFresh.setProdName(clBasket.getProdName());
            insClFresh.setAddress(clBasket.getAddress());
            insClFresh.setProvince(clBasket.getProvince());
            insClFresh.setDistrict(clBasket.getDistrict());
            insClFresh.setSubdistrict(clBasket.getSubdistrict());
            insClFresh.setComment(clBasket.getComment());
            insClFresh.setCreateDate(DateHelper.toDateTime(clBasket.getCreateDate()));
            insClFresh.setModifyDate(DateHelper.toDateTime(clBasket.getModifyDate()));
            insClFresh.setModifyBy(clBasket.getModifyBy());
            insClFresh.setLeadStatus(clBasket.getStatus());
            insClFresh.setCustomerEmail(clBasket.getEmail());
            insClFresh.setClickId(clBasket.getClickId());
            insClFresh.setUserDefin01(clBasket.getAttribute1());
            insClFresh.setUserDefin02(clBasket.getAttribute2());
            insClFresh.setUserDefin03(clBasket.getAttribute3());
            insClFresh.setAmount(clBasket.getAmount());
            insClFresh.setOfferId(clBasket.getOfferId());
            insClFresh.setAgcOfferId(clBasket.getAgcOfferId());
            insClFresh.setTerms(clBasket.getTerms());
            insClFresh.setPrice(clBasket.getPrice());
            insClFresh.setUnit(clBasket.getUnit());
            insClFresh.setSubid1(clBasket.getSubid1());
            insClFresh.setSubid2(clBasket.getSubid2());
            insClFresh.setCustomerAge(clBasket.getCustomerAge());
            insClFresh.setCustomerEmail(clBasket.getCustomerEmail());
            insClFresh.setCustomerComment(clBasket.getCustomerComment());
            insClFresh.setIn_agc_offer_id(clBasket.getIn_agc_offer_id());
            insClFresh.setTrackerId(clBasket.getTrackerId());
            insClFresh.setIpAddress(clBasket.getIpAddress());
            insClFreshList.add(insClFresh);
        }
        return insClFreshList;
    }

}
