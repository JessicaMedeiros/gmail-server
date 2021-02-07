package io.github.jetmedeiros.gmailserver.dao;

import io.github.jetmedeiros.gmailserver.model.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1l;


    private Integer id;
    private String username;
    private String password;

    public UserDTO(){

    }

    public UserDTO(User obj) {
        id = obj.getId();
        username = obj.getUsername();
        password = obj.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
