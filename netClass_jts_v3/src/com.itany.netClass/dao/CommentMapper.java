package com.itany.netClass.dao;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentMapper {

    public List<Comment> findByParams(Comment comment);
}
