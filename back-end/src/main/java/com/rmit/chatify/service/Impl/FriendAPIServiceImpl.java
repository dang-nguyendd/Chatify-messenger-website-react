package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.FriendDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.model.Friend;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendAPIServiceImpl extends APIService implements FriendService {

    @Override
    public Boolean addFriend(Friend inputFriend) {
        System.out.println("FriendAPIServiceImpl.addFriend");
        Optional<UserDTO> user1 = userRepository.findById(inputFriend.getId1());
        Optional<UserDTO> user2 = userRepository.findById(inputFriend.getId2());
        System.out.println("Reach here");
        if (user1.isPresent() && user2.isPresent()) {
            //Find friend by user1 and user2 using custom query
//            List<FriendDTO> foundFriend = friendRepository.findFriendByUsers(user1.get(), user2.get());
//            System.out.println("Found friend: " + foundFriend);
//            if (foundFriend.size() > 0) {
//                return false;
//            }
            FriendDTO friend = addFriendHelper(user1, user2);

            return true;
        }
        return null;
    }

    private FriendDTO addFriendHelper(Optional<UserDTO> user1, Optional<UserDTO> user2) {
        FriendDTO friend = new FriendDTO();
        friend.getUsers().add(user1.get());
        friend.getUsers().add(user2.get());
        friendRepository.save(friend);
        //Create conversation
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.getParticipants().add(user1.get());
        conversationDTO.getParticipants().add(user2.get());
        conversationDTO.setConversationName(user1.get().getUsername() + "-" + user2.get().getUsername());
        conversationDTO.setConversationPhoto("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
        conversationRepository.save(conversationDTO);
        return friend;
    }

    @Override
    public Page<FriendDTO> getFriendOfUser(Long id, int page, int size) {
        //Get friend of user by user id
        Optional<UserDTO> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            //Get all friend of a user
            Page<FriendDTO> foundFriend = friendRepository.findFriendByUserId(foundUser.get(), PageRequest.of(page, size));
            return foundFriend;
        } else {
            return null;
        }
    }

    //Get friend name of user id
    @Override
    public List<UserDTO> getFriendNameByUserId(Long id) {
        Optional<UserDTO> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            //Get all friend of a user
            List<UserDTO> foundFriend = (List<UserDTO>) friendRepository.findFriendsByUserDTO(foundUser.get(), Pageable.unpaged());
            //Filter userid
            foundFriend.removeIf(user -> user.getId().equals(id));
            return foundFriend;
        } else {
            return null;
        }
    }


    @Override
    public Boolean removeFriend(Long friendId) {
        //Remove friend by friend id
        Optional<FriendDTO> friendDTO = friendRepository.findById(friendId);
        if (friendDTO.isPresent()) {
            friendRepository.delete(friendDTO.get());
            //Delete conversation
           return true;
        }
        return false;
    }

    @Override
    public String blockedFriend(Friend friend) {
        List<FriendDTO> foundFriend = friendRepository.findFriendByUsers(userRepository.findById(friend.getId1()).get(), userRepository.findById(friend.getId2()).get());
        if (foundFriend.size() > 0) {
            FriendDTO friendDTO = foundFriend.get(0);
            //Find conversation and set status to blocked
            List<ConversationDTO> conversationDTOList = conversationRepository.findFriendConversationGroupByUsers(userRepository.findById(friend.getId1()).get(), userRepository.findById(friend.getId2()).get());
            //Check if the available conversation is group or not
            String res = blockedConversationHelper(friend, friendDTO, conversationDTOList);
            if (res != null) return res;
        }
        return "Blocked failed";
    }

    private String blockedConversationHelper(Friend friend, FriendDTO friendDTO, List<ConversationDTO> conversationDTOList) {
        if (conversationDTOList.size() > 0) {
            ConversationDTO conversationDTO = conversationDTOList.get(0);
            conversationDTO.setIsBlocked(friend.getIsBlocked());
            conversationRepository.save(conversationDTO);
            friendDTO.setIsBlocked(friend.getIsBlocked());
            friendRepository.save(friendDTO);
            return "Block status" + friend.getIsBlocked();
        }
        return null;
    }


}