package com.tms.dto.request.clFresh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdClFreshs {
    private String json;
    private String timeZone;

    public UpdClFreshs(){
        // set default for time zone
        this.timeZone = "Asia/Ho_Chi_Minh";
    }
}
