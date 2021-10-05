package com.example.conduit.article.exceptions;

import com.example.conduit.exceptions.notfound.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
