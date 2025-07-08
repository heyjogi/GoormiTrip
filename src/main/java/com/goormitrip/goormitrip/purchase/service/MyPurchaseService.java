package com.goormitrip.goormitrip.purchase.service;

import java.util.List;

import com.goormitrip.goormitrip.purchase.dto.MyPurchaseHistoryResponse;

public interface MyPurchaseService {
    List<MyPurchaseHistoryResponse> getPurchaseHistoryByUserId(Long userId);
}
