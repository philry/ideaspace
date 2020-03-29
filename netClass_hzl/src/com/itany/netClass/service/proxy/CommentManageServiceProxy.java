package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Comment;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class CommentManageServiceProxy implements CommentManageService {
    TransactionManager tran=ObjectFactory.getObject("transaction");
    CommentManageService commentManageServiceTarget=ObjectFactory.getObject("commentManageServiceTarget");

    @Override
    public List<Comment> findByParam(Comment comment) {
        tran.beginTransaction();
        List<Comment> comments = commentManageServiceTarget.findByParam(comment);
        tran.commit();
        return comments;
    }
}
