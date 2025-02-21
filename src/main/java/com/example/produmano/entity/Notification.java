package com.example.produmano.entity;


import com.example.produmano.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime notificationDate;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String telegramChatId;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
}
