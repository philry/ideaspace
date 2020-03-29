package entity;

import java.io.Serializable;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/24 16:15
 * Description:
 * version:1.0
 */
public class Product implements Serializable {

    private Integer id;
    private String name;
    private Double price;

    public Product() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
