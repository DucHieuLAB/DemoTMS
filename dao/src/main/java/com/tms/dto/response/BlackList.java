package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlackList {
    private Integer blId;
    private Integer orgId;
    private Integer leadId;
    private String leadName;
    private String phone;
    private Integer prodId;
    private String comment;
    private Integer modifyby;
    private LocalDateTime modifydate;
}
