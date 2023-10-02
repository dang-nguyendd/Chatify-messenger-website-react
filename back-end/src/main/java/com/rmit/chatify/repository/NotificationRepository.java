package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.NotificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationDTO, Long> {
}
