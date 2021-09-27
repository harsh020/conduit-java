package com.example.conduit.article;

import com.example.conduit.base.Base;
import com.example.conduit.tag.Tag;
import com.example.conduit.user.models.UserProfile;
import com.example.conduit.utils.Translation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Base {
//    @Id  //TODO[#5]: Make slug as primary key for articles
    private String slug;
    private String title;
    private String description;
    private String body;

    @OneToOne
    private UserProfile author;

    //TODO[#5]: Add comments when created entity
//    @OneToMany
//    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "article_favourited",
            joinColumns = @JoinColumn(name = "article_slug"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<UserProfile> favorited;

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_slug"),
            inverseJoinColumns =  @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Article(String title, String description, String body, UserProfile author, Set<Tag> tags) {
        this.slug = Translation.toSlug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.author = author;
        this.tags = tags;
    }
}
