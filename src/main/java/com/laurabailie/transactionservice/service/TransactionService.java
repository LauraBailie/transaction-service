package com.laurabailie.transactionservice.service;

import com.laurabailie.transactionservice.exception.TransactionNotFoundException;
import com.laurabailie.transactionservice.model.Transaction;
import com.laurabailie.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Transaction createTransaction(Transaction transaction) {
        // Additional business logic if needed
        return repository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }
}