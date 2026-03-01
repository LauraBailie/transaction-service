package com.laurabailie.transactionservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type;       // "INCOME", "EXPENSE", "TRANSFER"

    private String category;   // e.g. "Salary", "Groceries"

    private String description;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    // optional
    //private Long relatedTransactionId;
}