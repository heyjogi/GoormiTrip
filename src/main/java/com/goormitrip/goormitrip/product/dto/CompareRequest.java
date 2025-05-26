package com.goormitrip.goormitrip.product.dto;

import java.util.List;
import lombok.Data;

@Data
public class CompareRequest {
    private List<Long> ids;
}
