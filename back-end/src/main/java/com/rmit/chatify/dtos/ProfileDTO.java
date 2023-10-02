package com.rmit.chatify.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.chatify.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Cacheable
@Table(name = "profiles", schema = "public")
public class ProfileDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection // 1
    @CollectionTable(name = "img_list", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "img") // 3
    @JsonIgnore
    private List<String> images;


    //TODO: Need to create relationship to link with user profile

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private UserDTO userDTO;

    @Column(name = "bio")
    private String bio;

    //Spreading profile model into profile DTO
    public static ProfileDTO from(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setImages(profile.getImages());
        profileDTO.setBio(profile.getBio());
        return profileDTO;
    }
}
