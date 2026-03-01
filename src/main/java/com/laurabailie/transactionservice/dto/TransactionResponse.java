package com.laurabailie.transactionservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String type;
    private String category;
    private String description;
    private LocalDateTime date;
}