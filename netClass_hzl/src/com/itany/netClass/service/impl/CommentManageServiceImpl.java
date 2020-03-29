package com.itany.netClass.service.impl;

import com.itany.netClass.dao.CommentManageMapper;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;

import java.util.List;

public class CommentManageServiceImpl implements CommentManageService {
    CommentManageMapper  commentManageMapper=ObjectFactory.getObject("commentManageMapper");

    @Override
    public List<Comment> findByParam(Comment comment) {
        List<Comment> comments = commentManageMapper.findByParam(comment);
        return comments;
    }
}
