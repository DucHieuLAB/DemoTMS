package com.tms.api.service;

import com.tms.api.exception.TMSDbException;
import com.tms.dto.request.saleOrder.ValidSaleOrder;

public interface ValidateSoService {
    boolean validSaleOrder( ValidSaleOrder validSaleOrder) throws TMSDbException;
}
