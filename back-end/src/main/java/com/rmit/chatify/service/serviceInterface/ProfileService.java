package com.rmit.chatify.service.serviceInterface;

import com.rmit.chatify.dtos.ProfileDTO;
import com.rmit.chatify.model.Profile;
import com.rmit.chatify.model.ResponseObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
    public List<ProfileDTO> getAll();

    public ProfileDTO add(Profile profile);

    public ProfileDTO update(Profile profile, Long id);

    public void delete(long id);

    public ProfileDTO getProfileById(long id);

    public ResponseEntity<ResponseObject> createProfile(String bio);

    public Boolean addImage(Long id, String image);

    //TODO: Pagination get gallery of user
    //Get gallery of user
    public Page<String> getGalleryByUserId(Long userId, int page, int size);
}