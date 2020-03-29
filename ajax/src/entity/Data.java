package entity;

import java.io.Serializable;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/25 10:57
 * Description:
 * version:1.0
 */
public class Data implements Serializable {

    private Integer id;
    private String spell;
    private String message;

    public Data() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
