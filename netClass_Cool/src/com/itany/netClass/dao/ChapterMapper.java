package com.itany.netClass.dao;

import com.itany.netClass.entity.Chapter;

import java.util.List;

public interface ChapterMapper {
    /**
     * 根据课程id查询所有的章节
     * @param id
     * @return
     */
    public List<Chapter> findAllByCourseId(Integer id);
}
