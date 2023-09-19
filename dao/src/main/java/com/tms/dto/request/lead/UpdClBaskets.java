package com.tms.dto.request.lead;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdClBaskets {
    private String json;
    private String timeZone;

    public UpdClBaskets(){
        // set default for time zone
        this.timeZone = "Asia/Ho_Chi_Minh";
    }
}
