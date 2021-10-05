package com.example.conduit.comment;

import com.example.conduit.article.Article;
import com.example.conduit.base.Base;
import com.example.conduit.user.models.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Base {
    private String body;

    @OneToOne
    private UserProfile user;

    @ManyToOne
    private Article article;
}
