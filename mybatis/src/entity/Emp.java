package entity;

import java.io.Serializable;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 9:57
 * Description:
 * version:1.0
 */
public class Emp implements Serializable {

    private Integer id;
    private String name;
    private Double salary;
    private Dept dept;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", dept=" + dept +
                '}';
    }

    public Emp() {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
