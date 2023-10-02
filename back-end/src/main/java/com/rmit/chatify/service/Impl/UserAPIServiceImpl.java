package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ProfileDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.model.User;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Cacheable(value = "users")
public class UserAPIServiceImpl extends APIService implements UserService {
    @Override
//    @Cacheable(value = "users")
    public List<UserDTO> getAllUsers() {
        //Check if the cache data is exist
        List<UserDTO> users = userRepository.findAll();
        if (users.size() > 0) {
            return users;
        }
        return null;
    }

    @Override
//    @Cacheable(value = "users", key = "#id")
    public UserDTO findById(Long id) {
        Optional<UserDTO> foundUser = userRepository.findById(id);
        return foundUser.get();
        //Find user by id in redis cache if not found find in database and populate the redis cache
        //Populate redis cache
    }

    @Override
//    @CachePut(cacheNames = "user", key = "#newUserDTO.id")
    public ResponseEntity<ResponseObject> insertUser(User newUserDTO) {
        UserDTO foundUserDTOS = userRepository.findByEmail(newUserDTO.getEmail().trim());

        UserDTO res = userRepository.save(UserDTO.from(newUserDTO));

        return foundUserDTOS != null ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Email already taken", "")) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok",
                                "query user successfully",
                                res
                        ));
    }

    @Override
//    @CachePut(cacheNames = "user", key = "#id")
    public ResponseEntity<ResponseObject> updateUser(User newUserDTO, Long id) {
        //Update user
        Optional<UserDTO> foundUser = userRepository.findById(id);
    // Check if the value in the cache
        if (foundUser.isPresent()) {
            foundUser.get().setUsername(newUserDTO.getUsername());
            foundUser.get().setAvatar(newUserDTO.getAvatar());
            foundUser.get().setStatus(newUserDTO.getStatus());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "query user successfully", userRepository.save(foundUser.get())));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find user with id: ", ""));
    }

    //Create new user and profile from user schema
    public ResponseEntity<ResponseObject> createUser(User newUser) {
        //Create user and save profile
        UserDTO foundUserDTOS = userRepository.findByEmail(newUser.getEmail());

        if (foundUserDTOS != null) {
            return ResponseEntity.status(HttpStatus.IM_USED).body(
                    new ResponseObject("failed", "Email already taken", ""));
        }
        //Create profile and user
        ProfileDTO newProfileDTO = new ProfileDTO();
        UserDTO userDTO = UserDTO.from(newUser);
        newProfileDTO.setUserDTO(userDTO);
        userDTO.setProfile(newProfileDTO);
        userRepository.save(userDTO);
        profileRepository.save(newProfileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create user successfully", newUser));
    }

    //Find user by email
    @Override
    public ResponseEntity<ResponseObject> findByEmail(String email) {
        UserDTO foundUserDTOS = userRepository.findByEmail(email);
        return foundUserDTOS != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "query user successfully", foundUserDTOS)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find user with email: ", ""));
    }

    @Override
//    @Cacheable(cacheNames = "user", key = "#id", unless = "#result == null")
    public ResponseEntity<ResponseObject> findByUsername(String username, int page, int size) {
        //Find user by user name
        //First check if user is available in redis cache
        Page<UserDTO> foundUserDTOS = userRepository.findByUsername(username, PageRequest.of(page, size));

        if (foundUserDTOS.hasContent()) {
            //Populate redis cache
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "query user successfully", foundUserDTOS));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find user with username: ", ""));
    }
}
