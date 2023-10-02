package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ProfileDTO;
import com.rmit.chatify.model.Profile;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.APIService;
import com.rmit.chatify.service.serviceInterface.ProfileService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@CacheConfig(cacheNames = "profileCache")
@Service
public class ProfileAPIServiceImpl extends APIService implements ProfileService {

    //    @Cacheable(cacheNames = "profile")
    @Override
    @Cacheable(value = "profiles")
    public List<ProfileDTO> getAll() {
        return profileRepository.findAll();
    }

    //    @CacheEvict(cacheNames = "profile", allEntries = true)
    @Override
    public ProfileDTO add(Profile profile) {
        ProfileDTO newProfileDTO = ProfileDTO.from(profile);
        ProfileDTO res = profileRepository.save(newProfileDTO);
        return res;
    }

    //    @CacheEvict(cacheNames = "profile", allEntries = true)
    @Override
    @CachePut(value = "profiles", key = "#id")
    public ProfileDTO update(Profile profile, Long id) {
        Optional<ProfileDTO> optProfile = profileRepository.findById(id);
        if (!optProfile.isPresent())
            return null;
        ProfileDTO repProfileDTO = optProfile.get();
        repProfileDTO.setBio(profile.getBio());
        repProfileDTO.setImages(profile.getImages());
        ProfileDTO res = profileRepository.save(repProfileDTO);
        return res;
    }

    //    @Caching(evict = {@CacheEvict(cacheNames = "profile", key = "#id"),
//            @CacheEvict(cacheNames = "profile", allEntries = true)})
    @Override
    @CacheEvict(value = "profiles", key = "#id")
    public void delete(long id) {
        profileRepository.deleteById(id);

    }

    //    @Cacheable(cacheNames = "profile", key = "#id", unless = "#result == null")
    @Override
    @Cacheable(value = "profiles", key = "#id")
    public ProfileDTO getProfileById(long id) {
        waitSomeTime();
        Optional<ProfileDTO> optProfile = profileRepository.findById(id);
        return optProfile.orElse(null);
    }


    @Override
    public ResponseEntity<ResponseObject> createProfile(String bio) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setBio(bio);
        profileRepository.save(profileDTO);
        return ResponseEntity.ok(new ResponseObject("ok", "create profile successfully", profileDTO));
    }

    @Override
    @Cacheable(cacheNames = "images", key = "#id", unless = "#result == null")
    public Boolean addImage(Long id, String image) {
        //Add image to profile
        Optional<ProfileDTO> optProfile = profileRepository.findById(id);
        if (!optProfile.isPresent())
            return false;
        ProfileDTO repProfileDTO = optProfile.get();
        repProfileDTO.getImages().add(image);
        profileRepository.save(repProfileDTO);
        return true;
    }

    @Override
    @Cacheable(cacheNames = "images", key = "#userId", unless = "#result == null")
    public Page<String> getGalleryByUserId(Long userId, int page, int size) {
        Page<String> res = profileRepository.getGalleryByUserId(userId, PageRequest.of(page,size));
        if (res.hasContent()) {
            return res;
        }
        return null;
        //Get images of user
    }

    private void waitSomeTime() {
        System.out.println("Long Wait Begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Long Wait End");
    }
}
