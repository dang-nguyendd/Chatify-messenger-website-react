package com.rmit.chatify.repository;

import com.rmit.chatify.dtos.FriendDTO;
import com.rmit.chatify.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendDTO, Long> {
    //Find if friendship exists between 2 users
    @Query("SELECT f FROM FriendDTO f WHERE ?1 member of f.users and ?2 member of f.users")
    List<FriendDTO> findFriendByUsers(UserDTO user1, UserDTO user2);

//    @Query("SELECT f FROM FriendDTO f WHERE ?1 member of f.users")
//    Page<FriendDTO> findFriendByUserId(UserDTO userDTO, Pageable pageable);

    @Query("SELECT f FROM FriendDTO f WHERE ?1 member of f.users")
    Page<FriendDTO> findFriendByUserId(UserDTO userDTO, Pageable pageable);

    ///Get userdto object of user's friend list
    @Query("SELECT f.users FROM FriendDTO f WHERE ?1 member of f.users")
    Page<UserDTO> findFriendsByUserDTO(UserDTO userDTO, Pageable pageable);
}

