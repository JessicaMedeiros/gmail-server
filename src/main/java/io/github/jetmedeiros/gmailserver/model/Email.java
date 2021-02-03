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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Email(){

    }

    public Email(Integer id, String content, String title, boolean isread, Date date, User user) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.isread = isread;
        this.date = date;
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return isread == email.isread && id.equals(email.id) && content.equals(email.content) && title.equals(email.title) && user.equals(email.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title, isread, user);
    }
}
