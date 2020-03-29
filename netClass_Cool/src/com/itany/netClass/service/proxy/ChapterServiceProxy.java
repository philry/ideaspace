package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.Chapter;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ChapterService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class ChapterServiceProxy implements ChapterService {
    @Override
    public List<Chapter> findAllByCourseId(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("tran");
        ChapterService chapterService = ObjectFactory.getObject("chapterServiceTarget");
        tran.beginTransaction();
        List<Chapter> chapters = chapterService.findAllByCourseId(id);
        tran.commit();
        return chapters;
    }
}
