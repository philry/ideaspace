package com.itany.netClass.entity;

import java.io.Serializable;

public class CourseType implements Serializable {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer status;

    public CourseType() {
    }

    public CourseType(Integer id, String name, Integer parentId, Integer status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                '}';
    }
}
