package io.github.jetmedeiros.gmailserver.dao;

import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Integer id;
    private String content;
    private String title;
    private boolean isread;
    private boolean favorite;
    private Integer idUser;
    private Date date;


    public EmailDTO(){

    }

    public EmailDTO(Email obj) {
        id = obj.getId();
        content = obj.getContent();
        title = obj.getTitle();
        isread = obj.isIsread();
        idUser = obj.getUser().getId();
        date = obj.getDate();
        favorite = obj.isIsread();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
