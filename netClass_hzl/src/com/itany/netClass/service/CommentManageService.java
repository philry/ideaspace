package com.itany.netClass.service;

import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentManageService {
    /**
     * 评论审核页面根据条件查找
     * @param comment
     */
    public List<Comment> findByParam(Comment comment);
}
