package com.example.conduit.user;

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

    @ManyToMany
    @JoinTable(
            name="user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follow_id")
    )
    private Set<User> following;

    @ManyToMany(mappedBy = "following")
    private Set<User> follow;

    public void followUser(User user) {
        if(!following.contains(user)) {
            following.add(user);
        }
        else {
            following.remove(user);
        }
    }

    public void followedBy(User user) {
        if(!follow.contains(user)) {
            follow.add(user);
        }
        else {
            follow.remove(user);
        }
    }
}
