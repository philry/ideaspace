package com.itany.netClass.service;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> findByParams(Comment comment);
}
