package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CommentServiceProxy implements CommentService {
    @Override
    public List<Comment> findByParams(Comment comment) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        CommentService commentService =ObjectFactory.getObject("commentServiceTarget");
        try {
            tran.beginTransaction();
            List<Comment> comments = commentService.findByParams(comment);
            tran.commit();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
