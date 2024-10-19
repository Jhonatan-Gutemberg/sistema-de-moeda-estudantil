package com.coinsystem.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Transaction;
import com.coinsystem.system.repository.TransactionRepository;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Transaction>>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            ApiResponse<List<Transaction>> response = new ApiResponse<>(true,
                    "Transações obtidas com sucesso", transactions);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse<List<Transaction>> errorResponse = new ApiResponse<>(false,
                    "Erro ao obter transações", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
