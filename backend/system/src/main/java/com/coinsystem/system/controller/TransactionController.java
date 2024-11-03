package com.coinsystem.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Transaction;
import com.coinsystem.system.repository.TransactionRepository;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Presents currency transaction statement using student ID.", description = "Return transaction ID, student ID,amount,description, timestamp and ID teacher.")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Transaction>>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            ApiResponse<List<Transaction>> response = new ApiResponse<>(true,
                    "Transactions retrieved successfully.", transactions);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse<List<Transaction>> errorResponse = new ApiResponse<>(false,
                    "Error retrieving transactions.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Presents statement of coinstransaction between teacher and student", description = "Return transaction ID, student ID,amount,description, timestamp and ID teacher.")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactionsByStudent(
            @PathVariable Long studentId) {
        try {
            List<Transaction> transactions = transactionRepository.findByStudentId(studentId);
            if (transactions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "No transactions found for the student.", null));
            }

            ApiResponse<List<Transaction>> response = new ApiResponse<>(true,
                    "Transactions retrieved successfully.", transactions);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error retrieving transactions", null));
        }
    }

}
