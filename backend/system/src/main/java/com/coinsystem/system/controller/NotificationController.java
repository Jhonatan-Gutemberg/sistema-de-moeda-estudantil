package com.coinsystem.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Notification;
import com.coinsystem.system.service.interfaces.IExchangeService;
import com.coinsystem.system.service.interfaces.INotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private IExchangeService exchangeService;

    @Autowired
    private INotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Notification>>> getAllNotification() {
        try {
            List<Notification> notification = notificationService.getAllNotifications();
            ApiResponse<List<Notification>> response = new ApiResponse<>(true, "All Notification fetched successfully",
                    notification);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Notification>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/{notificationId}/confirm-student")
    public ResponseEntity<ApiResponse<Void>> confirmStudentReceipt(@PathVariable Long notificationId) {
        try {
            exchangeService.confirmStudentReceipt(notificationId);
            ApiResponse<Void> response = new ApiResponse<>(true, "Retirada confirmada pelo aluno", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, "Erro ao confirmar retirada", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/{notificationId}/confirm-partner")
    public ResponseEntity<ApiResponse<Void>> confirmPartnerReceipt(@PathVariable Long notificationId) {
        try {
            exchangeService.confirmPartnerReceipt(notificationId);
            ApiResponse<Void> response = new ApiResponse<>(true, "Retirada confirmada pela empresa parceira", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, "Erro ao confirmar retirada", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
