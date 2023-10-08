package com.tms.api.service;

import com.tms.api.exception.TMSException;
import com.tms.dto.request.saleOrder.ValidSaleOrder;

public interface ValidateSoService {
    boolean validSaleOrder( ValidSaleOrder validSaleOrder) throws TMSException;
}
