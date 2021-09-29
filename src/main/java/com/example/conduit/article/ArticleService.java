package com.example.conduit.article;

import com.example.conduit.tag.Tag;
import com.example.conduit.tag.TagRepository;
import com.example.conduit.tag.TagService;
import com.example.conduit.user.models.User;
import com.example.conduit.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    UserRepository userRepository;
    TagRepository tagRepository;
    ArticleRepository articleRepository;

    TagService tagService;

    public ArticleService(UserRepository userRepository, TagRepository tagRepository,
                          ArticleRepository articleRepository, TagService tagService) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
        this.tagService = tagService;
    }

    public Article createArticle(String title, String description, String body,
                                 User author, List<String> tagList) {
        //TODO[#6]: Make Mony relationship consitent by making the fields as List<> instead of Set<>
        //TODO[#12]: Create a createTags function in tagService to create tags from list

        List<Tag> tags = null;
        for(String name: tagList) {
            //TODO[#13]: check if tag already exist, if not create new
            tags.add(tagService.createTag(name, ""));
        }

        Article article = new Article(
                title,
                description,
                body,
                author.getProfile(),
                (Set<Tag>) tags
        );
        return articleRepository.save(article);
    }

    public Article getArticlesBySlug(String slug) {
        //TODO[#7]: Add ArticleNotFoundException
        return articleRepository.findArticleBySlug(slug);
    }

    public List<Article> getArticlesByAuthor(String author) {
        //TODO[#8]: Add InvalidAuthorException
        User user = userRepository.findUserByProfileUsername(author);
        return articleRepository.findArticlesByAuthor(user.getProfile());
    }

    public List<Article> getArticlesByTag(String tagname) {
        //TODO[#9]: Add InvalidTagException
        Tag tag = tagRepository.findTagByTitle(tagname);
        return articleRepository.findArticlesByTag(tag);
    }

    public List<Article> getArticlesByFavorited(String username) {
        //TODO[#10]: Add InvalidFavorited
        User user = userRepository.findUserByProfileUsername(username);
        return articleRepository.findArticlesByFavorited(user.getProfile());
    }

    public List<Article> getArticlesByFilters(String author, String tagname, String favorite) {
        //TODO[#11]: Find some decent way to build complex filters
        List<Article> articles = (List<Article>) articleRepository.findAll();
        if(author!=null) {
            articles = articles.stream()
                    .distinct()
                    .filter(getArticlesByAuthor(author)::contains)
                    .collect(Collectors.toList());
        }

        if(tagname!=null) {
            articles = articles.stream()
                    .distinct()
                    .filter(getArticlesByTag(tagname)::contains)
                    .collect(Collectors.toList());
        }

        if(favorite!=null) {
            articles = articles.stream()
                    .distinct()
                    .filter(getArticlesByFavorited(favorite)::contains)
                    .collect(Collectors.toList());
        }

        return articles;
    }

    public Article updateArticle(String slug, String newTitle, String newDesc, String newBody, List<String> newTags) {
        List<Tag> tagList = null;
        for(String tag: newTags) {
            Tag t = tagService.getTagByTitle(tag);
            if(t!=null) tagList.add(t);
            else tagList.add(tagService.createTag(tag, ""));
        }
        Article article = articleRepository.findArticleBySlug(slug);
        if(newTitle != null) article.setTitle(newTitle);
        if(newDesc != null) article.setDescription(newDesc);
        if(newBody != null) article.setBody(newBody);
        if(tagList != null) article.setTags((Set<Tag>) tagList);

        return articleRepository.save(article);
    }

}
