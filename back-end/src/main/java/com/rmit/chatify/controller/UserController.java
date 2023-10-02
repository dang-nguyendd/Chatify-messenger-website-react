package com.rmit.chatify.controller;

import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.model.User;
import com.rmit.chatify.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserController {
    //DI = Dependency Injection
    @Autowired
    UserService service;

    @GetMapping("")
        //this request is: http://localhost:5432/Users
    List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        UserDTO res = service.findById(id);
        if (res == null) {
            return ResponseEntity.ok(new ResponseObject("404","User not found", null));
        }
        return ResponseEntity.ok(new ResponseObject("200","User found", res));
    }

//    @PostMapping("/insert")
//    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
//        return service.insertUser(newUser);
//    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return service.updateUser(newUser, id);
    }

    @GetMapping("/search/{email}")
    ResponseEntity<ResponseObject> searchByEmail(@PathVariable String email) {
        ResponseEntity<ResponseObject> res = service.findByEmail(email);
        return res;
    }

    //Create user
    @PostMapping("/create")
    ResponseEntity<ResponseObject> createUser(@RequestBody User newUser) {
        return service.createUser(newUser);
    }

    //Find user by user name
    @GetMapping("/search/{username}")
    ResponseEntity<ResponseObject> findByUsername(@PathVariable String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.findByUsername(username, page, size);
    }

}