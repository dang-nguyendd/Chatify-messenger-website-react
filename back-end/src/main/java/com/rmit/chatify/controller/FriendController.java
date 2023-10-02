package com.rmit.chatify.controller;


import com.rmit.chatify.dtos.FriendDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.Friend;
import com.rmit.chatify.model.Notification;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.serviceInterface.FriendService;
import com.rmit.chatify.service.serviceInterface.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/friends")
@CrossOrigin(origins = "*")
public class FriendController {
    @Autowired
    private FriendService service;
    @Autowired
    private SearchService searchService;

//    @Autowired
//    private ProducerServiceImpl producerService;

    //    Get user by email
    @GetMapping("/search")
    ResponseEntity<ResponseObject> findUserByEmail(@RequestParam String email, @RequestParam int page, @RequestParam int size) {
        try {
            List<UserDTO> res = searchService.getUserBasedOnEmail(email, page, size);
            if (res == null) {
                return ResponseEntity.ok(new ResponseObject("404", "User not found", null));
            }
            return ResponseEntity.ok(new ResponseObject("200", "User found", res));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "User Module in server error", null));
        }
    }

    // Search username by email
    @GetMapping("/search/name")
    ResponseEntity<ResponseObject> findUserByName(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        try {
            List<UserDTO> res = searchService.getUserBasedOnName(name, page, size);
            if (res == null) {
                return ResponseEntity.ok(new ResponseObject("404", "User not found", null));
            }
            return ResponseEntity.ok(new ResponseObject("200", "User found", res));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "User search in server error", null));
        }
    }


    //Add friend
    @PostMapping("/addFriend")
    public ResponseEntity<ResponseObject> addFriend(@RequestBody Friend inputFriend) {
        Notification notification = new Notification();
        try {
//            notification.setContent("You have a new friend request");
//            notification.getUserId().add(inputFriend.getId2());
//            producerService.sendNotification(notification);
            Boolean res = service.addFriend(inputFriend);
            if (res == null) {
                return ResponseEntity.ok(new ResponseObject("404", "User not found", null));
            } else if (res == false) {
                return ResponseEntity.ok(new ResponseObject("400", "User already friend", null));
            }
            return ResponseEntity.ok(new ResponseObject("200", "Add friend successfully", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "User Module in server error", null));
        }
    }

    //Find friend name by user id
    @GetMapping("/getFriendNameByUserId/{id}")
    public ResponseEntity<ResponseObject> getFriendNameByUserId(@PathVariable Long id) {
        try {
            List<UserDTO> res = service.getFriendNameByUserId(id);
            if (res == null){
                return ResponseEntity.ok(new ResponseObject("404", "User not found", null));
            }
            return ResponseEntity.ok(new ResponseObject("200", "Get friend name successfully", res));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Friend Module in server error", null));
        }
    }

    //Get friend of user
    @GetMapping("/getFriendOfUser/{id}")
    public ResponseEntity<ResponseObject> getFriendOfUser(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Page<FriendDTO> res = service.getFriendOfUser(id, page, size);
            if (res == null){
                return ResponseEntity.ok(new ResponseObject("404", "User not found", null));
            }
            return ResponseEntity.ok(new ResponseObject("200", "Get friend successfully", res));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Friend Module in server error", null));
        }

    }

    //Remove friend
    @DeleteMapping("/removeFriend/{friendId}")
    public ResponseEntity<ResponseObject> removeFriend(@PathVariable Long friendId) {
        try {

            return service.removeFriend(friendId) ?
                    ResponseEntity.ok(new ResponseObject("200", "Remove friend successfully", true)) :
                    ResponseEntity.ok(new ResponseObject("404", "User not found", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Friend Module in server error", null));
        }
    }

    //Blocked friend
    @PostMapping("/blockedFriend")
    public ResponseEntity<ResponseObject> blockedFriend(@RequestBody Friend friend) {
        try {
            String res =service.blockedFriend(friend);
            return ResponseEntity.ok(new ResponseObject("200", res, null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("500", "Friend Module in server error", null));
        }
    }
}