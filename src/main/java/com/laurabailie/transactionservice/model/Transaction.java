package com.laurabailie.transactionservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data  // Lombok for getters/setters
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Currency must not be blank")
    private String currency;

    private String description;

    private String status = "PROCESSED";  // Default

    private LocalDateTime timestamp = LocalDateTime.now();  // Auto-set
}