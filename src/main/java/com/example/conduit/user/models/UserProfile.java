package com.example.conduit.user.models;

import com.example.conduit.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    public UserProfile(String username) {
        this.username = username;
    }

    @ManyToMany
    @JoinTable(
            name="user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follow_id")
    )
    private Set<UserProfile> following;

    @ManyToMany(mappedBy = "following")
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
