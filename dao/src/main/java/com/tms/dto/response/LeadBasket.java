package com.tms.dto.response;

import com.tms.dto.request.lead.ClFresh;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadBasket implements Serializable {
    private int leadId;
    private String name;
    private String phone;
    private Integer prodId;
    private LocalDateTime createDate;
    private Integer status;
    private String attribute3;
    private String comment;

    public static ClFresh toInsLeadAfterFillter(LeadBasket basket){
        ClFresh CLFresh = new ClFresh();
        CLFresh.setLeadId(basket.leadId);
        CLFresh.setName(basket.name);
        CLFresh.setPhone(basket.getPhone());
        CLFresh.setProdId(basket.getProdId());
        CLFresh.setLeadStatus(basket.getStatus());
        CLFresh.setComment(basket.getComment());
        return CLFresh;
    }
}
