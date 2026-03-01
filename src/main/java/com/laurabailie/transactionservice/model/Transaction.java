package com.laurabailie.transactionservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

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

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type;  // "INCOME", "EXPENSE", "TRANSFER"

    private String category;  // e.g. "Salary", "Groceries", "Rent", "Entertainment"

    private String description;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    // Optional: reference to another transaction for transfers
    private Long relatedTransactionId;
}