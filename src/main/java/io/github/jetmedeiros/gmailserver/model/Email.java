package io.github.jetmedeiros.gmailserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Email implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private String title;
    private boolean isread;
    private Date date;
    private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cc_id")
    private User cc;

    public Email() {

    }

    public Email(Integer id, String content, String title, boolean isread, Date date, User user, boolean favorite, User cc) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.isread = isread;
        this.date = date;
        this.user = user;
        this.cc = cc;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCc() {
        return cc;
    }

    public void setCc(User cc) {
        this.cc = cc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return isread == email.isread && favorite == email.favorite && Objects.equals(id, email.id) && Objects.equals(content, email.content) && Objects.equals(title, email.title) && Objects.equals(date, email.date) && Objects.equals(user, email.user) && Objects.equals(cc, email.cc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title, isread, date, favorite, user, cc);
    }
}
