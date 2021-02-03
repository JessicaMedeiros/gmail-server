package io.github.jetmedeiros.gmailserver.dao;

import io.github.jetmedeiros.gmailserver.controller.UsuarioController;
import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.model.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1l;


    private Integer id;
    private String nome;
    private String senha;

    public UserDTO(){

    }

    public UserDTO(User obj) {
        id = obj.getId();
        nome = obj.getNome();
        senha = obj.getSenha();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
