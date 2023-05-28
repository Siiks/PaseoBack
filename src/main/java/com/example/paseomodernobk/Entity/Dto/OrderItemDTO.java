package com.example.paseomodernobk.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private OrderDTO orderId;

    private Integer quantity;

    private BigDecimal price;

    private ProductDTO product;
}
