package com.rmit.chatify.controller;

import com.rmit.chatify.dtos.ProfileDTO;
import com.rmit.chatify.model.Profile;
import com.rmit.chatify.model.ResponseObject;
import com.rmit.chatify.service.serviceInterface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    ProfileService service;

    // Get all employees.
    // Url - http://localhost:9000/api/profile/getall
    @GetMapping("/getall")
    public List<ProfileDTO> findAll() {
        final List<ProfileDTO> profileDTOList = service.getAll();
        // Todo - If developers like they can sort the map (optional).
        return profileDTOList;
    }

    // Url - http://localhost:9000/api/profile/employee/get/<employee_id>
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") final long id) {
        // Get employee by id.

        ProfileDTO res = service.getProfileById(id);
        if (res == null) {
            return ResponseEntity.ok(new ResponseObject("404","Profile not found", false));
        }
        return ResponseEntity.ok(new ResponseObject("200","Profile found",  res));
    }

    //Get gallery of user
    @GetMapping("/get/gallery/{id}")
    public ResponseEntity<ResponseObject> getGalleryByUserId(@PathVariable("id") final long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        // Get employee by id.
        Page<String> res = service.getGalleryByUserId(id, page, size);
        if (res == null) {
            return ResponseEntity.ok(new ResponseObject("404","Gallery not found", false));
        }
        return ResponseEntity.ok(new ResponseObject("200","Gallery found",  res));
    }

    // Delete employee by id.
    // Url - http://localhost:9000/api/profile/delete/<employee_id>
    @DeleteMapping("/delete/{id}")
    public List<ProfileDTO> delete(@PathVariable("id") final long id) {
        service.delete(id);
        return findAll();
    }


    // Update employee by id.
    // Url - http://localhost:9000/api/profile/update
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody final Profile profile, @PathVariable("id") final long id) {
        ProfileDTO resProfile =  service.update(profile, id);
        if(resProfile == null){
            return ResponseEntity.ok(new ResponseObject("404","Profile not found", false));
        }
        return ResponseEntity.ok(new ResponseObject("200","Profile updated",  resProfile));
    }

    //     Add image to employee by id.
//     Url - http://localhost:9000/api/profile/addImage/<employee_id>
    @PutMapping("/addImage/{id}")
    public ResponseEntity<ResponseObject> addImage(@PathVariable("id") final long id, @RequestBody final String image) {
        if(service.addImage(id, image)){
            return ResponseEntity.ok(new ResponseObject("200","Image added",  true));
        }
        return ResponseEntity.ok(new ResponseObject("404","Profile not found", false));
    }


    // Create profile
    // Url - http://localhost:9000/api/profile/createProfile
    @PostMapping("/createProfile")
    public ResponseEntity<ResponseObject> createProfile(@RequestBody final String bio) {
        return service.createProfile(bio);
    }
}
