package com.example.conduit.user.models;

import com.example.conduit.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends Base {
    private String username;
    private String bio;
    private String image;

    //TODO[#1]: add articles favourited
    //TODO[#2}: add authored articles

    public UserProfile(String username) {
        this.username = username;
    }

    @ManyToMany(fetch = FetchType.EAGER) //TODO[#0]: Find reasonable fix, as lazily loading will give error in response
    @JoinTable(
            name="user_following",
            joinColumns = @JoinColumn(name = "user_id"), //TODO[#3]: change name to profile_id
            inverseJoinColumns = @JoinColumn(name = "follow_id")
    )
    private Set<UserProfile> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private Set<UserProfile> follow;

    public void followUser(User user) {
        if(!following.contains(user.getProfile())) {
            following.add(user.getProfile());
        }
        else {
            following.remove(user.getProfile());
        }
    }

    public void followedBy(User user) {
        if(!follow.contains(user.getProfile())) {
            follow.add(user.getProfile());
        }
        else {
            follow.remove(user.getProfile());
        }
    }
}
