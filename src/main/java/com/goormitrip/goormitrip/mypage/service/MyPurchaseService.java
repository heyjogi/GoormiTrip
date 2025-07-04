package com.goormitrip.goormitrip.mypage.service;

import java.util.List;
import com.goormitrip.goormitrip.mypage.dto.MyPurchaseHistoryResponse;

public interface MyPurchaseService {
    List<MyPurchaseHistoryResponse> getPurchaseHistoryByUserId(Long userId);
}
