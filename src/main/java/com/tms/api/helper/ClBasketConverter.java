package com.tms.api.helper;

import com.tms.dto.request.clBasket.UpdClBasket;
import com.tms.dto.response.ClBasket;

import java.util.List;
import java.util.stream.Collectors;

public class ClBasketConverter {
    public static List<UpdClBasket> convertToUpdClBasketList(List<ClBasket> clBasketList) {
        return clBasketList.stream()
                .map(clBasket -> {
                    UpdClBasket updClBasket = new UpdClBasket();
                    updClBasket.setLeadId(clBasket.getLeadId());
                    updClBasket.setAgcId(clBasket.getAgcId());
                    updClBasket.setAgcCode(clBasket.getAgcCode());
                    updClBasket.setOrgId(clBasket.getOrgId());
                    updClBasket.setCcCode(clBasket.getCcCode());
                    updClBasket.setName(clBasket.getName());
                    updClBasket.setPhone(clBasket.getPhone());
                    updClBasket.setProdId(clBasket.getProdId());
                    updClBasket.setProdName(clBasket.getProdName());
                    updClBasket.setAddress(clBasket.getAddress());
                    updClBasket.setProvince(clBasket.getProvince());
                    updClBasket.setDistrict(clBasket.getDistrict());
                    updClBasket.setSubdistrict(clBasket.getSubdistrict());
                    updClBasket.setComment(clBasket.getComment());
                    updClBasket.setIn_createdate(DateHelper.toDateTime(clBasket.getCreateDate()));
                    updClBasket.setModifydate(DateHelper.toDateTime(clBasket.getModifyDate()));
                    updClBasket.setModifyby(clBasket.getModifyBy());
                    updClBasket.setStatus(clBasket.getStatus());
                    updClBasket.setEmail(clBasket.getEmail());
                    updClBasket.setQuantity(clBasket.getQuantity());
                    updClBasket.setClickId(clBasket.getClickId());
                    updClBasket.setAgKey(clBasket.getAgKey());
                    updClBasket.setIn_attribute1(clBasket.getAttribute1());
                    updClBasket.setIn_attribute2(clBasket.getAttribute2());
                    updClBasket.setIn_attribute3(clBasket.getAttribute3());
                    updClBasket.setAmount(clBasket.getAmount());
                    updClBasket.setOfferId(clBasket.getOfferId());
                    updClBasket.setAgcOfferId(clBasket.getAgcOfferId());
                    updClBasket.setTerms(clBasket.getTerms());
                    updClBasket.setPrice(clBasket.getPrice());
                    updClBasket.setUnit(clBasket.getUnit());
                    updClBasket.setClickId2(clBasket.getClickId2());
                    updClBasket.setAffiliateId(clBasket.getAffiliateId());
                    updClBasket.setSubid3(clBasket.getSubid3());
                    updClBasket.setSubid4(clBasket.getSubid4());
                    updClBasket.setSubid5(clBasket.getSubid5());
                    updClBasket.setNetworkid(clBasket.getNetworkid());
                    updClBasket.setPid(clBasket.getPid());
                    updClBasket.setTrackingUrlId(clBasket.getTrackingUrlId());
                    updClBasket.setSubid1(clBasket.getSubid1());
                    updClBasket.setSubid2(clBasket.getSubid2());
                    updClBasket.setCustomerAge(clBasket.getCustomerAge());
                    updClBasket.setCustomerEmail(clBasket.getCustomerEmail());
                    updClBasket.setCustomerComment(clBasket.getCustomerComment());
                    updClBasket.setAgcoffer_id(clBasket.getIn_agc_offer_id());
                    updClBasket.setCreateDate(DateHelper.toDateTime(clBasket.getCreate_date()));
                    updClBasket.setLinkClickId(clBasket.getLinkClickId());
                    updClBasket.setDuplicateMainLead(clBasket.getDuplicateMainLead());
                    updClBasket.setLeadIp(clBasket.getLeadIp());
                    updClBasket.setDuplicateTime(clBasket.getDuplicateTime());
                    updClBasket.setTrackerId(clBasket.getTrackerId());
                    updClBasket.setIpAddress(clBasket.getIpAddress());
                    return updClBasket;
                })
                .collect(Collectors.toList());
    }
}
