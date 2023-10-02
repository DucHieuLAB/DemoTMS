package com.tms.dto.response;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GetLeadForAgentDto  implements Serializable {
    private Integer leadId;
    private Integer agcId;
    private String agcCode;
    private Integer orgID;
    private String ccCode;
    private String name;
    private String phone;
    private Integer prodId;
    private String prodName;
    private Integer assigned;
    private Integer calleby;
    private String comment;
    private String address;
    private  String province;
    private String lead_status;
    private Integer dayCall;
    private Integer totalCall;

}
