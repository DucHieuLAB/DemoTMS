package com.tms.dto.response;

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
public class CfBlackList implements Serializable {
    private Integer blId;
    private Integer orgId;
    private Integer leadId;
    private String leadName;
    private String phone;
    private Integer prodId;
    private String comment;
    private Integer modifyby;
    private LocalDateTime modifydate;

    @Override
    public String toString() {
        return "CfBlackList{" +
                "blId=" + blId +
                ", orgId=" + orgId +
                ", leadId=" + leadId +
                ", leadName='" + leadName + '\'' +
                ", phone='" + phone + '\'' +
                ", prodId=" + prodId +
                ", comment='" + comment + '\'' +
                ", modifyby=" + modifyby +
                ", modifydate=" + modifydate +
                '}';
    }
}
