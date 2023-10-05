package com.tms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClBasket implements Comparable<ClBasket>{
    private Integer leadId;
    private Integer agcId;
    private String agcCode;
    private Integer orgId;
    private String ccCode;
    private String name;
    private String phone;
    private Integer prodId;
    private String prodName;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Integer modifyBy;
    private Integer status;
    private String email;
    private String quantity;
    private String clickId;
    private String agKey;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String amount;
    private String offerId;
    private String agcOfferId;
    private String terms;
    private String price;
    private String unit;
    private String clickId2;
    private String affiliateId;
    private String subid3;
    private String subid4;
    private String subid5;
    private String networkid;
    private String pid;
    private String trackingUrlId;
    private String subid1;
    private String subid2;
    private Integer customerAge;
    private String customerEmail;
    private String customerComment;
    private String in_agc_offer_id;
    private LocalDateTime create_date;
    private String linkClickId;
    private String duplicateMainLead;
    private String leadIp;
    private Integer duplicateTime;
    private Integer trackerId;
    private String ipAddress;

    // public static ClFresh toInsLeadAfterFillter(ClBasket basket){
    //     ClFresh CLFresh = new ClFresh();
    //     CLFresh.setLeadId(basket.leadId);
    //     CLFresh.setName(basket.name);
    //     CLFresh.setPhone(basket.getPhone());
    //     CLFresh.setProdId(basket.getProdId());
    //     CLFresh.setLeadStatus(basket.getStatus());
    //     CLFresh.setComment(basket.getComment());
    //     return CLFresh;
    // }

    @Override
    public String toString() {
        return "ClBasket{" +
                "leadId=" + leadId +
                ", agcId=" + agcId +
                ", agcCode='" + agcCode + '\'' +
                ", orgId=" + orgId +
                ", ccCode='" + ccCode + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", subdistrict='" + subdistrict + '\'' +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", modifyBy=" + modifyBy +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", quantity='" + quantity + '\'' +
                ", clickId='" + clickId + '\'' +
                ", agKey='" + agKey + '\'' +
                ", attribute1='" + attribute1 + '\'' +
                ", attribute2='" + attribute2 + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", amount='" + amount + '\'' +
                ", offerId='" + offerId + '\'' +
                ", agcOfferId='" + agcOfferId + '\'' +
                ", terms='" + terms + '\'' +
                ", price='" + price + '\'' +
                ", unit='" + unit + '\'' +
                ", clickId2='" + clickId2 + '\'' +
                ", affiliateId='" + affiliateId + '\'' +
                ", subid3='" + subid3 + '\'' +
                ", subid4='" + subid4 + '\'' +
                ", subid5='" + subid5 + '\'' +
                ", networkid='" + networkid + '\'' +
                ", pid='" + pid + '\'' +
                ", trackingUrlId='" + trackingUrlId + '\'' +
                ", subid1='" + subid1 + '\'' +
                ", subid2='" + subid2 + '\'' +
                ", customerAge=" + customerAge +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerComment='" + customerComment + '\'' +
                ", in_agc_offer_id='" + in_agc_offer_id + '\'' +
                ", create_date=" + create_date +
                ", linkClickId='" + linkClickId + '\'' +
                ", duplicateMainLead='" + duplicateMainLead + '\'' +
                ", leadIp='" + leadIp + '\'' +
                ", duplicateTime=" + duplicateTime +
                ", trackerId=" + trackerId +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }

    @Override
    public int compareTo(ClBasket o) {
        if (o.createDate.isAfter(this.createDate)){
            return 1;
        }else if(o.createDate.isBefore(this.createDate)){
            return -1;
        }else{
            return 0;
        }
    }
}
