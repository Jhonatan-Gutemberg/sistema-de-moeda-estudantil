package com.coinsystem.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsystem.system.model.Notification;
import com.coinsystem.system.repository.NotificationRepository;
import com.coinsystem.system.service.interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}
