package com.laurabailie.transactionservice.service;

import com.laurabailie.transactionservice.dto.TransactionRequest;
import com.laurabailie.transactionservice.dto.TransactionResponse;
import com.laurabailie.transactionservice.model.Transaction;
import com.laurabailie.transactionservice.repository.TransactionRepository;
import com.laurabailie.transactionservice.repository.UserRepository;
import com.laurabailie.transactionservice.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    private Transaction testTransaction;
    private TransactionRequest testRequest;

    @BeforeEach
    void setUp() {
        // Mock security context (prevents NPE on getAuthentication().getName())
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", null);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        // Mock user lookup (service calls userRepository.findByUsername(...))
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(mockUser));
        testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setAmount(BigDecimal.valueOf(100.00)); // ← use BigDecimal
        testTransaction.setType("INCOME"); // ← now exists
        testTransaction.setCategory("Salary"); // ← now exists
        testTransaction.setDescription("Test salary");

        testRequest = new TransactionRequest();
        testRequest.setAmount(BigDecimal.valueOf(100.00));
        testRequest.setType("INCOME");
        testRequest.setCategory("Salary");
        testRequest.setDescription("Test salary");
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createTransaction_shouldSaveAndReturnTransactionResponse() {
        // Given
        when(repository.save(any(Transaction.class))).thenReturn(testTransaction);

        // When
        TransactionResponse result = service.createTransaction(testRequest);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(BigDecimal.valueOf(100.00), result.getAmount());
        assertEquals("INCOME", result.getType());
        assertEquals("Salary", result.getCategory());

        verify(repository, times(1)).save(any(Transaction.class));
    }

    // You can leave these commented until you add getTransactionById() to the
    // service
    /*
     * @Test
     * void getTransactionById_shouldReturnTransaction_whenFound() { ... }
     * 
     * @Test
     * void getTransactionById_shouldThrowException_whenNotFound() { ... }
     */
}