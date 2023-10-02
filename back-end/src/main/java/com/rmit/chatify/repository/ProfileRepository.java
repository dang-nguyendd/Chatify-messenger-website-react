package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.ProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProfileRepository extends JpaRepository<ProfileDTO, Long> {

//    Get galleray of user
    //Select images of user
    //Get images with pagination of ProfileDTO
    @Query(value = "SELECT p.img FROM img_list p WHERE p.id = ?1", nativeQuery = true)
    Page<String> getGalleryByUserId(Long userId, Pageable pageable);
}
