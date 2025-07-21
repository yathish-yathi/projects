package com.yathish.order_management_system.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderRequest {

    private Long userId;
    private List<OrderLine> orderLine;

    @Data
    @AllArgsConstructor
    public static class OrderLine {
        private Long productId;
        private Integer quantity;
    }
    
}
