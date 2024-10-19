package com.coinsystem.system.DTO;

import java.time.LocalDateTime;

public record NotificationDTO(
                Long id,
                String coupon,
                LocalDateTime dateShipping,
                LocalDateTime dateReceipt,
                Long partnerCompanyId,
                String partnerCompanyName,
                Long studentId,
                String studentName,
                boolean studentConfirmed,
                boolean partnerConfirmed) {

}
