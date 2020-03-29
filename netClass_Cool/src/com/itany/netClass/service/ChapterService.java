package com.itany.netClass.service;

import com.itany.netClass.entity.Chapter;

import java.util.List;

public interface ChapterService {

    /**
     * 根据课程id查询所有的章节
     * @return
     */
    public List<Chapter> findAllByCourseId(Integer id);
}
