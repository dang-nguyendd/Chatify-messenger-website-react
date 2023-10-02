package com.rmit.chatify.service.Impl;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.rmit.chatify.dtos.NotificationDTO;
import com.rmit.chatify.model.Notification;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.NotificationService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends APIService implements NotificationService {

    @Override
    @Cacheable(value = "notification", key = "#notification.id")
    public NotificationDTO createNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setContent(notification.getContent());
        //Retrieve user in notifcation users array
        for (Long i : notification.getUserId()) {
            userRepository.findById(i).ifPresent(user -> {
                notificationDTO.getUsers().add(user);
            });
        }
        notificationRepository.save(notificationDTO);
        System.out.println("Notification saved id" + notificationDTO.getId());
        return notificationDTO;
    }
}
