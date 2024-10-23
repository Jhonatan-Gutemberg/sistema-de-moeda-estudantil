package com.coinsystem.system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coinsystem.system.DTO.ExchangeDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.service.interfaces.IExchangeService;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    @Autowired
    private IExchangeService exchangeService;

    @PostMapping("/{studentId}/vantage/{vantageId}")
    public ResponseEntity<ApiResponse<ExchangeDTO>> exchangeVantage(
            @PathVariable Long studentId,
            @PathVariable Long vantageId) {
        try {
            ExchangeDTO exchangeDTO = exchangeService.exchangeVantage(studentId, vantageId);
            ApiResponse<ExchangeDTO> response = new ApiResponse<>(true,
                    "Exchange completed successfully", exchangeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse<ExchangeDTO> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ApiResponse<ExchangeDTO> errorResponse = new ApiResponse<>(false,
                    "Error while processing the exchange", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{studentId}/history")
    public ResponseEntity<ApiResponse<List<ExchangeDTO>>> getExchangeHistory(@PathVariable Long studentId) {
        try {
            List<ExchangeDTO> exchanges = exchangeService.getExchangeHistory(studentId);
            ApiResponse<List<ExchangeDTO>> response = new ApiResponse<>(true,
                    "Exchange history retrieved successfully.", exchanges);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            ApiResponse<List<ExchangeDTO>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ApiResponse<List<ExchangeDTO>> errorResponse = new ApiResponse<>(false,
                    "Error retrieving exchange history.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
