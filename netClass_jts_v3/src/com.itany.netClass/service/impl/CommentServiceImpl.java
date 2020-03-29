package com.itany.netClass.service.impl;

import com.itany.netClass.dao.CommentMapper;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> findByParams(Comment comment) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        List<Comment> comments = commentMapper.findByParams(comment);
        return comments;
    }
}
