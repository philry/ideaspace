package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 9:56
 * Description:
 * version:1.0
 */
public class Dept implements Serializable {

    private Integer id;
    private String name;
    private List<Emp> emps = new ArrayList<Emp>();

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emps=" + emps +
                '}';
    }

    public Dept() {
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
}
