package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CommentServiceProxy implements CommentService {
    @Override
    public List<Comment> findAllWait() {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        List<Comment> comments = commentService.findAllWait();
        tran.commit();
        return comments;
    }

    @Override
    public void modifyEnable(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        commentService.modifyEnable(id);
        tran.commit();
    }

    @Override
    public void modifyDisable(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        commentService.modifyDisable(id);
        tran.commit();
    }

    @Override
    public List<Comment> findByData(Comment comment) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        List<Comment> comments = commentService.findByData(comment);
        tran.commit();
        return comments;
    }

    @Override
    public List<Comment> findByChapterId(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        List<Comment> comments = commentService.findByChapterId(id);
        tran.commit();
        return comments;
    }

    @Override
    public List<Comment> findAllPassed() {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        List<Comment> comments = commentService.findAllPassed();
        tran.commit();
        return comments;
    }

    @Override
    public void modifyById(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        commentService.modifyById(id);
        tran.commit();
    }

    @Override
    public List<Comment> findPassedByData(Comment comment) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        CommentService commentService = ObjectFactory.getObject("commentServiceTarget");
        tran.beginTransaction();
        List<Comment> comments = commentService.findPassedByData(comment);
        tran.commit();
        return comments;
    }
}
