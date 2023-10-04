package com.tms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignInf implements Serializable {
    private Integer cpId;
    private Integer cpcfId;
    private Integer callinglistId;
    private String clName;
    private String cpName;
    private Integer productId;

    @Override
    public String toString() {
        return "CampaignInf{" +
                "cpId=" + cpId +
                ", cpcfId=" + cpcfId +
                ", callinglistId=" + callinglistId +
                ", cl_name='" + clName + '\'' +
                ", cpName='" + cpName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
