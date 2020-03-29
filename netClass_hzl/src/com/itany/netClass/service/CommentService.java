package com.itany.netClass.service;

public interface CommentService {
    /**
     * 启用或禁用评论
     * @param id
     */
    public void passOrRejectComment(Integer id);
}
