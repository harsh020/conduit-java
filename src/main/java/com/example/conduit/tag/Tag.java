package com.example.conduit.tag;

import com.example.conduit.base.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends Base {
    private String title;
    private String description;

    //TODO[#4]: Add set of articles
}
