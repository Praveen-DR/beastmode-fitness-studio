package com.beastmode.services.notification_service;

import com.beastmode.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;

    NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }




}
