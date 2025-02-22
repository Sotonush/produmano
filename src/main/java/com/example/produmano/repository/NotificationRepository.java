package com.example.produmano.repository;

import com.example.produmano.entity.Notification;
import com.example.produmano.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByNotificationDateBeforeAndStatus(LocalDateTime notificationDate, NotificationStatus status);
}
