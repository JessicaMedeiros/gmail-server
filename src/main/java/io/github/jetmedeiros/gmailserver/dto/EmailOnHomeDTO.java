package io.github.jetmedeiros.gmailserver.dto;

import io.github.jetmedeiros.gmailserver.model.Email;

import java.io.Serializable;
import java.util.Date;

public class EmailOnHomeDTO  implements Serializable {
        private static final long serialVersionUID = 1l;

        private Integer id;
        private String content;
        private String title;
        private boolean isread;
        private boolean favorite;
        private String username;
        private Date date;
        private String cc;

        public EmailOnHomeDTO(){

        }

    public EmailOnHomeDTO(Email obj) {
        id = obj.getId();
        content = obj.getContent();
        title = obj.getTitle();
        isread = obj.isIsread();
        username = obj.getUser().getUsername();
        date = obj.getDate();
        favorite = obj.isIsread();
        cc = obj.getCc().getUsername();
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
