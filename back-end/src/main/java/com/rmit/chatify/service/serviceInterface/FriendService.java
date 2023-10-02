package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.FriendDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.Friend;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FriendService {
    //Add friend
    public Boolean addFriend(Friend inputFriend);

    //Get friend of user
    //TODO: Pagination
    public Page<FriendDTO> getFriendOfUser(Long id, int page, int size);

    //Get friend name by user id
    public List<UserDTO> getFriendNameByUserId(Long id);

    //Remove friend
    public Boolean removeFriend(Long friendId);

    //Blocked friend
    public String blockedFriend(Friend friend);

}