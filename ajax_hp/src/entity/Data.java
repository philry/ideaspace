package entity;

import java.io.Serializable;

public class Data implements Serializable {
    private Integer id;
    private String spell;
    private String message;

    public Data() {
    }

    public Data(Integer id, String spell, String message) {
        this.id = id;
        this.spell = spell;
        this.message = message;
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

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", spell='" + spell + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
