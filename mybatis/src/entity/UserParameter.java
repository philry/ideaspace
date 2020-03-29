package entity;

import java.io.Serializable;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/26 15:18
 * Description:
 * version:1.0
 */
public class UserParameter implements Serializable {

    private String username;
    private String password;

    public UserParameter() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
