package com.itany.netClass.dao;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentManageMapper {
    public List<Comment> findAll();

    public Comment findById(Integer id);

    /**
     *评论审核页面根据条件查询
     * @param comment
     * @return
     */
    public List<Comment> findByParam(Comment comment);
}
