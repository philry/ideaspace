package com.itany.netClass.service;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentManageService {

    public List<Comment> findByParams(Comment comment);

    public void passCommentById(Integer id);

    public void refuseCommentById(Integer id);
}
