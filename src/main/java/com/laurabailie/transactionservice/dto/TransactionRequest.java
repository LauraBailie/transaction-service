package com.laurabailie.transactionservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "INCOME|EXPENSE|TRANSFER", message = "Type must be INCOME, EXPENSE or TRANSFER")
    private String type;

    private String category;

    private String description;

    // For TRANSFER type: optional target user/account ID or similar
}