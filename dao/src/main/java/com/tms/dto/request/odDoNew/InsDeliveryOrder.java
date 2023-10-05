package com.tms.dto.request.odDoNew;


import com.tms.DaoConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.jvm.hotspot.debugger.cdbg.EnumType;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsDeliveryOrder {
    private Integer orgId;
    private Integer doId;
    private String doCode;
    private String ffmCode;
    private String trackingCode;
    private Integer soId;
    private Integer ffmId;
    private Integer warehouseId;
    private Integer carrierId;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String customerWards;
    private String customerDistrict;
    private String customerProvince;
    private String packageId;
    private String packageName;
    private String packageDescription;
    private String packageListItem;
    private Double amountcod;
    private Integer status;
    private String statusFfm;
    private String statusLastmile;
    private String errorCode;
    private String errorMessage;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private Integer createby;
    private String createdate;
    private Integer updateby;
    private String updatedate;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String approvedTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String expectedDeliveryTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String expectedArrivalTime;
    private String ffmReason;
    private String ffmReasonDetail;
    private String lastmileReason;
    private String lastmileReasonDetail;
    private String lastmileReturnCode;
    private Integer rescueId;
    private String ffmReturnCode;
    private Integer rcActionMappingId;
    private Integer isActive;
    private String customerNeighborhood;
    private String customerPostalCode;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String firstdelivertime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String returntime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String closetime;
    private Integer isPostback;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String pickedUpDate;
    private Integer attemp;
    private Integer paidCod;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String paidCodDate;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String secondDeliveryTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String thirdDeliveryTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String appointmentDate;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String pickedTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String packedTime;
    @Pattern(regexp = DaoConst.DATE_TIME_REGEX, message = "{date-time.Pattern.message}")
    private String handoveredTime;
    private String deliveryPackageCode;
    private String returnTrackingCode;

    public String convertToValueQuery() {
        return  "("+orgId +
                ',' + (doId == null ? "nextval('seq_do_id')":doId)+
                "," + (doCode == null ? "null" : "'" + doCode + "'") +
                "," + (ffmCode == null ? "null" : "'" + ffmCode + "'") +
                "," + (trackingCode == null ? "null" : "'" + trackingCode + "'") +
                "," + soId +
                "," + ffmId +
                "," + warehouseId +
                "," + carrierId +
                "," + customerId +
                "," + (customerName == null ? "null" : "'" + customerName + "'") +
                "," + (customerPhone == null ? "null" : "'" + customerPhone + "'") +
                "," + (customerAddress == null ? "null" : "'" + customerAddress + "'") +
                "," + (customerWards == null ? "null" : "'" + customerWards + "'") +
                "," + (customerDistrict == null ? "null" : "'" + customerDistrict + "'") +
                "," + (customerProvince == null ? "null" : "'" + customerProvince + "'") +
                "," + (packageId == null ? "null" : "'" + packageId + "'") +
                "," + (packageName == null ? "null" : "'" + packageName + "'") +
                "," + (packageDescription == null ? "null" : "'" + packageDescription + "'") +
                "," + (packageListItem == null ? "null" : "'" + packageListItem + "'") +
                "," + amountcod +
                "," + status +
                "," + (statusFfm == null ? "null" : "'" + statusFfm + "'") +
                "," + (statusLastmile == null ? "null" : "'" + statusLastmile + "'") +
                "," + (errorCode == null ? "null" : "'" + errorCode + "'") +
                "," + (errorMessage == null ? "null" : "'" + errorMessage + "'") +
                "," + (attribute1 == null ? "null" : "'" + attribute1 + "'") +
                "," + (attribute2 == null ? "null" : "'" + attribute2 + "'") +
                "," + (attribute3 == null ? "null" : "'" + attribute3 + "'") +
                "," + (attribute4 == null ? "null" : "'" + attribute4 + "'") +
                "," + (attribute5 == null ? "null" : "'" + attribute5 + "'") +
                "," + createby +
                "," + (createdate == null ? "null" : "'" + createdate + "'") +
                "," + updateby +
                "," + (updatedate == null ? "null" : "'" + updatedate + "'") +
                "," + (approvedTime == null ? "null" : "'" + approvedTime + "'") +
                "," + (expectedDeliveryTime == null ? "null" : "'" + expectedDeliveryTime + "'") +
                "," + (expectedArrivalTime == null ? "null" : "'" + expectedArrivalTime + "'") +
                "," + (ffmReason == null ? "null" : "'" + ffmReason + "'") +
                "," + (ffmReasonDetail == null ? "null" : "'" + ffmReasonDetail + "'") +
                "," + (lastmileReason == null ? "null" : "'" + lastmileReason + "'") +
                "," + (lastmileReasonDetail == null ? "null" : "'" + lastmileReasonDetail + "'") +
                "," + (lastmileReturnCode == null ? "null" : "'" + lastmileReturnCode + "'") +
                "," + rescueId +
                "," + (ffmReturnCode == null ? "null" : "'" + ffmReturnCode + "'") +
                "," + rcActionMappingId +
                "," + isActive +
                "," + (customerNeighborhood == null ? "null" : "'" + customerNeighborhood + "'") +
                "," + (customerPostalCode == null ? "null" : "'" + customerPostalCode + "'") +
                "," + (firstdelivertime == null ? "null" : "'" + firstdelivertime + "'") +
                "," + (returntime == null ? "null" : "'" + returntime + "'") +
                "," + (closetime == null ? "null" : "'" + closetime + "'") +
                "," + isPostback +
                "," + (pickedUpDate == null ? "null" : "'" + pickedUpDate + "'") +
                "," + attemp +
                "," + paidCod +
                "," + (paidCodDate == null ? "null" : "'" + paidCodDate + "'") +
                "," + (secondDeliveryTime == null ? "null" : "'" + secondDeliveryTime + "'") +
                "," + (thirdDeliveryTime == null ? "null" : "'" + thirdDeliveryTime + "'") +
                "," + (appointmentDate == null ? "null" : "'" + appointmentDate + "'") +
                "," + (pickedTime == null ? "null" : "'" + pickedTime + "'") +
                "," + (handoveredTime == null ? "null" : "'" + handoveredTime + "'") +
                "," + (deliveryPackageCode == null ? "null" : "'" + deliveryPackageCode + "'") +
                "," + (returnTrackingCode == null ? "null" : "'" + returnTrackingCode + "'")+")";
    }
}