package com.coinsystem.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsystem.system.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
