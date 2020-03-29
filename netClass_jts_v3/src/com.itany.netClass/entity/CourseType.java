package com.itany.netClass.entity;

import java.io.Serializable;

public class CourseType implements Serializable {
    private Integer id;
    private String typeName;
    private CourseType parentType;
    private Integer status;

    @Override
    public String toString() {
        return "CourseType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentType=" + parentType +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CourseType getParentType() {
        return parentType;
    }

    public void setParentType(CourseType parentType) {
        this.parentType = parentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CourseType(Integer id, String typeName, CourseType parentType, Integer status) {

        this.id = id;
        this.typeName = typeName;
        this.parentType = parentType;
        this.status = status;
    }

    public CourseType() {

    }
}
