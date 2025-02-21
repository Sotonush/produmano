package com.example.produmano.telegram;

import com.example.produmano.entity.Notification;
import com.example.produmano.entity.Task;
import com.example.produmano.enums.NotificationStatus;
import com.example.produmano.repository.NotificationRepository;
import com.example.produmano.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {

    private final TelegramNotificationService telegramService;
    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;

    public NotificationScheduler(TelegramNotificationService telegramService,
                                 NotificationRepository notificationRepository,
                                 TaskRepository taskRepository) {
        this.telegramService = telegramService;
        this.notificationRepository = notificationRepository;
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 600_000)
    public void sendPendingNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findByEndDateBetween(now, now.plusWeeks(1));

        for (Task task : tasks) {
            String messageText = "Задача \"" + task.getDescription() + "\" должна быть выполнена через неделю.";

            Notification notification = new Notification();
            notification.setTaskId(task.getId());
            notification.setNotificationDate(now.plusDays(7));
            notification.setMessage(messageText);
            notification.setTelegramChatId(task.getClient().getTelegramChatId());
            notification.setStatus(NotificationStatus.NOT_SENT);

            notificationRepository.save(notification);

            telegramService.sendNotification(notification.getTelegramChatId(), notification.getMessage());

            notification.setStatus(NotificationStatus.SENT);
            notificationRepository.save(notification);
        }
    }
}
