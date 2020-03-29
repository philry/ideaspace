package com.itany.netClass.service.impl;

import com.itany.netClass.dao.CommentMapper;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> findAllWait() {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");

        List<Comment> comments = commentMapper.findAllWait();

        return comments;
    }

    @Override
    public void modifyEnable(Integer id) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        commentMapper.modifyEnable(id);
    }

    @Override
    public void modifyDisable(Integer id) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        commentMapper.modifyDisable(id);

    }

    @Override
    public List<Comment> findByData(Comment comment) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");

        List<Comment> comments = commentMapper.findByData(comment);

        return comments;
    }

    @Override
    public List<Comment> findByChapterId(Integer id) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        List<Comment> comments = commentMapper.findByChapterId(id);
        if(comments.isEmpty()){
            System.out.println("error  some thing");
        }
        return comments;
    }

    @Override
    public List<Comment> findAllPassed() {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        List<Comment> comments = commentMapper.findAllPassed();
//        System.out.println("这里的comments的长度"+comments.size());
        return comments;
    }

    @Override
    public void modifyById(Integer id) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        commentMapper.modifyById(id);
    }

    @Override
    public List<Comment> findPassedByData(Comment comment) {
        CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
        List<Comment> comments = commentMapper.findPassedByData(comment);
        return comments;
    }
}
