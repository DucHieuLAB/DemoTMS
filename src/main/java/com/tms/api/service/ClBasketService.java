package com.tms.api.service;

import com.tms.api.exception.TMSException;
import com.tms.dto.request.clBasket.GetLeadToFillter;
import com.tms.dto.response.ClBasket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ClBasketService {

    List<ClBasket> getListToFillter(GetLeadToFillter getLeadToFillter) throws TMSException;

    List<ClBasket> getLeadInTimeRange(LocalDateTime startTime, LocalDateTime endTime) throws TMSException;

    List<ClBasket> getListToFilterProcess(String sessionId) throws TMSException;

    void updateClBasket(List<ClBasket> clBaskets, String sessionId, String timeZone) throws TMSException;
}
