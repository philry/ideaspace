package com.itany.netClass.dao;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentManageMapper {

    public List<Comment> findByParams(Comment comment);

    /**
     * 通过评论，将status属性改为0
     * @param id
     */
    public void passCommentById(Integer id);

    /**
     * 禁用评论，将status属性改为1
     * @param id
     */
    public void refuseCommentById(Integer id);
}
