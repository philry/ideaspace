package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CommentManageServiceProxy implements CommentManageService {

    TransactionManager tran = ObjectFactory.getObject("transaction");
    CommentManageService commentManageService = ObjectFactory.getObject("commentManageServiceTarget");
    @Override
    public List<Comment> findByParams(Comment comment) {

        List<Comment> comments = null;
        try {
            tran.beginTransaction();
            comments = commentManageService.findByParams(comment);
            tran.commit();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void passCommentById(Integer id) {
        try {
            tran.beginTransaction();
            commentManageService.passCommentById(id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void refuseCommentById(Integer id) {
        try {
            tran.beginTransaction();
            commentManageService.refuseCommentById(id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }
}
