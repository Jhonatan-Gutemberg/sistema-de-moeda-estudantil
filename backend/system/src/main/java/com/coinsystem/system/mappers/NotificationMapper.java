package com.coinsystem.system.mappers;

import com.coinsystem.system.DTO.NotificationDTO;
import com.coinsystem.system.model.Notification;
import com.coinsystem.system.model.PartnerCompany;
import com.coinsystem.system.model.Student;

public class NotificationMapper {

    public static Notification notificationDtoToModel(NotificationDTO notificationDTO, Student student,
            PartnerCompany partnerCompany) {
        return new Notification(
                notificationDTO.coupon(),
                partnerCompany,
                student);
    }

    
}
