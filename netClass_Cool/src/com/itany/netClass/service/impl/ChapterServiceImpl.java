package com.itany.netClass.service.impl;

import com.itany.netClass.dao.ChapterMapper;
import com.itany.netClass.entity.Chapter;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;

import java.util.List;

public class ChapterServiceImpl implements ChapterService {
    @Override
    public List<Chapter> findAllByCourseId(Integer id) {
        ChapterMapper chapterMapper = ObjectFactory.getObject("chapterMapper");
        List<Chapter> chapters = chapterMapper.findAllByCourseId(id);
        return chapters;
    }
}
