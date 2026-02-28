package com.laurabailie.transactionservice.service;

import com.laurabailie.transactionservice.exception.TransactionNotFoundException;
import com.laurabailie.transactionservice.model.Transaction;
import com.laurabailie.transactionservice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setAmount(100.0);
        testTransaction.setCurrency("ZAR");
    }

    @Test
    void createTransaction_shouldSaveAndReturnTransaction() {
        when(repository.save(any(Transaction.class))).thenReturn(testTransaction);
        Transaction result = service.createTransaction(testTransaction);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(testTransaction);
    }

    @Test
    void getTransactionById_shouldReturnTransaction_whenFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(testTransaction));
        Transaction result = service.getTransactionById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void getTransactionById_shouldThrowException_whenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> service.getTransactionById(1L));
    }
}