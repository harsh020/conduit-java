package com.example.conduit.user.models;

import com.example.conduit.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
// by default the table name is User which is a reserved word in PostgreSQL
@Table(name="ConduitUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {
    private String email;
    private String password;

    @OneToOne
    private UserProfile profile;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void followUser(User user) {
        profile.followUser(user);
    }

    public void followedBy(User user) {
        profile.followedBy(user);
    }
}
