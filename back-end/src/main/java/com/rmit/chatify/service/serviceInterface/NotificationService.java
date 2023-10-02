package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.NotificationDTO;
import com.rmit.chatify.model.Notification;

public interface NotificationService {
    NotificationDTO createNotificationDTO(Notification notification);
}
