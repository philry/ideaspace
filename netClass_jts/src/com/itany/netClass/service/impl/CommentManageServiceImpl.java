package com.itany.netClass.service.impl;

import com.itany.netClass.dao.CommentManageMapper;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;

import java.util.List;

public class CommentManageServiceImpl implements CommentManageService {
    @Override
    public List<Comment> findByParams(Comment comment) {
        CommentManageMapper commentManageMapper = ObjectFactory.getObject("commentManageMapper");
        List<Comment> comments = commentManageMapper.findByParams(comment);
        return comments;
    }

    @Override
    public void passCommentById(Integer id) {
        CommentManageMapper commentManageMapper = ObjectFactory.getObject("commentManageMapper");
        commentManageMapper.passCommentById(id);
    }

    @Override
    public void refuseCommentById(Integer id) {
        CommentManageMapper commentManageMapper = ObjectFactory.getObject("commentManageMapper");
        commentManageMapper.refuseCommentById(id);
    }
}
