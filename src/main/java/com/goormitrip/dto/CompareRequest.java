package com.goormitrip.dto;

import java.util.List;
import lombok.Data;

@Data
public class CompareRequest {
    private List<Long> ids;
}
