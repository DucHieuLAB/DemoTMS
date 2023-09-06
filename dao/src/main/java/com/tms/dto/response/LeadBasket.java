package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LeadBasket implements Serializable {
    private int leadid;
    private String name;
    private String phone;
    private int prod_id;
    private int status;
    private String attribute3;
}
