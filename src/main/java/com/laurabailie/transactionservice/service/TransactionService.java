package com.laurabailie.transactionservice.service;

import com.laurabailie.transactionservice.dto.TransactionRequest;
import com.laurabailie.transactionservice.dto.TransactionResponse;
import com.laurabailie.transactionservice.model.Transaction;
import com.laurabailie.transactionservice.model.User;
import com.laurabailie.transactionservice.repository.TransactionRepository;
import com.laurabailie.transactionservice.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public TransactionResponse createTransaction(TransactionRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());

        transaction = transactionRepository.save(transaction);

        return mapToResponse(transaction);
    }

    public List<TransactionResponse> getUserTransactions() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return transactionRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Optional: GET by ID, PUT update, DELETE

    private TransactionResponse mapToResponse(Transaction t) {
        TransactionResponse resp = new TransactionResponse();
        resp.setId(t.getId());
        resp.setAmount(t.getAmount());
        resp.setType(t.getType());
        resp.setCategory(t.getCategory());
        resp.setDescription(t.getDescription());
        resp.setDate(t.getDate());
        return resp;
    }
}