package com.itany.netClass.entity;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {

    private Integer id;
    private String context;
    private String createDate;
    private Integer userId;
    private Integer resourceId;
    private Integer status;
    private User user;
    private Integer count;//点赞次数
    private String startDate;//查询数据使用，开始时间
    private String endDate;//查询数据使用，结束时间

    public void setUserName(String loginName){
        User user = new User();
        user.setLoginName(loginName);
        this.setUser(user);
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private Resource resource;
    private List<Praise> praises;

    //初始化点赞数
    public void initCount(){
        if(praises != null ){
            this.count = this.praises.size();
        }
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Praise> getPraises() {
        return praises;
    }

    public void setPraises(List<Praise> praises) {
        this.praises = praises;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", createDate='" + createDate + '\'' +
                ", userId=" + userId +
                ", resourceId=" + resourceId +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
