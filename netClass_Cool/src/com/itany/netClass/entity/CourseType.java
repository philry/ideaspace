package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.List;

public class CourseType implements Serializable {
    private Integer id;
    private String typeName;
    private Integer parentId;
    private Integer status;
    private List<CourseType> childTypes;

    public List<CourseType> getChildTypes() {
        return childTypes;
    }

    public void setChildTypes(List<CourseType> childTypes) {
        this.childTypes = childTypes;
    }

    public CourseType() {
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentId=" + parentId +
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
}
