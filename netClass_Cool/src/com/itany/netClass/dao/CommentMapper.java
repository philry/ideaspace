package com.itany.netClass.dao;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.entity.Comment;

import java.util.List;

public interface CommentMapper {
    /**
     * 查询所有待审核评论
     * @return
     */
    public List<Comment> findAllWait();

    /**
     * 根据id设置评论通过审核
     * @param id
     */
    public void modifyEnable(Integer id);

    /**
     * 根据id设置评论不通过审核
     * @param id
     */
    public void modifyDisable(Integer id);

    /**
     * 根据条件查询
     * @param comment
     * @return
     */
    public List<Comment> findByData(Comment comment);

    /**
     * 根据章节id查询相关评论
     * @param id
     * @return
     */
    public List<Comment> findByChapterId(Integer id);

    /**
     * 查询所有的通过的审核的评论
     * @return
     */
    public List<Comment> findAllPassed();

    /**
     * 根据id修改已审核的评论的状态
     * @param id
     */
    public void modifyById(Integer id);

    /**
     * 根据条件查询所有已审核评论
     * @param comment
     * @return
     */
    public List<Comment> findPassedByData(Comment comment);
}
