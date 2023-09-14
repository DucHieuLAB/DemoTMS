package com.tms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ClBaskets implements Serializable {
    private List<ClBasket> clBaskets;
}
