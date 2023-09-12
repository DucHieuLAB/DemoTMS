package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeadBasket implements Serializable {
    private int leadId;
    private String name;
    private String phone;
    private Integer prodId;
    private LocalDateTime createDate;
    private Integer status;
    private String attribute3;
    private String comment;
}
