package com.example.conduit.user;

import com.example.conduit.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
// by default the table name is User which is a reserved word in PostgreSQL
@Table(name="ConduitUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {
    private String username;
    private String email;
    private String password;
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

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

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
