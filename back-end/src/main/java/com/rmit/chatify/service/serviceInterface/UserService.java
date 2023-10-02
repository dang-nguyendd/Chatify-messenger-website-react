package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO findById(@PathVariable Long id);

    ResponseEntity<ResponseObject>insertUser(@RequestBody User newUser);

    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id);

    ResponseEntity<ResponseObject> createUser(User newUser);

    //Find user by email
    ResponseEntity<ResponseObject> findByEmail(String email);

    //Find user by user name
    ResponseEntity<ResponseObject> findByUsername(String username, int page, int size);
}