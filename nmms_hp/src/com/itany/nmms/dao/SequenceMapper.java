package com.itany.nmms.dao;

import com.itany.nmms.entity.Sequence;
import org.apache.ibatis.annotations.Select;

public interface SequenceMapper {
    public void insert (Sequence sequence);

    /**
     * 按名字查是否存在这个名字的Sequence对象
     * @param name
     * @return
     */
    public Sequence SelectByName(String name);

    /**
     * 更新Sequence对象
     * @param sequence
     */
    public void update(Sequence sequence);
}
