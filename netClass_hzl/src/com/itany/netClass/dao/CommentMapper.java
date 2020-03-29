package com.itany.netClass.dao;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentMapper {
    public List<Comment> findAll();

    public Comment findById(Integer id);

    /**
     * 根据条件查询
     * @param commmnet
     * @return
     */
    public List<Comment> findByParam(Comment commmnet);
}
