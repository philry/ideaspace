package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Depart implements Serializable {
    private Integer id;
    private String name;
    private List<Emp> emps=new ArrayList<>();
    public Depart() {
    }

    public Depart(Integer id, String name, List<Emp> emps) {
        this.id = id;
        this.name = name;
        this.emps = emps;
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

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }
}
