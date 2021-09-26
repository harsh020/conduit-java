package com.example.conduit.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findTagByTitle(@Param("title") String title);
}
