package com.beastmode.mappers;

import com.beastmode.models.Notification;
import com.beastmode.models.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {
    public Notification toNotification(String notificationId, User userId, String message, String date) {
        return Notification.builder()
                .notificationId(notificationId)
                .user(userId)
                .message(message)
                .date(date)
                .build();
    }
}
