package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
    @Query("SELECT u FROM UserDTO u WHERE u.email like ?1")
    UserDTO findByEmail(String email);

    @Query(value = "SELECT u FROM users u WHERE u.user_name like ?2", nativeQuery = true)
    Page<UserDTO> findByUsername(String username, Pageable pageable);
}
