package model;

import java.sql.Date;

public class Tweet {

    private Long id;
    private AppUser author;
    private String message;
    private Date publishedAt;

    public Tweet() {
    }

    public Tweet(Long id, AppUser author, String message, Date publishedAt) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }


}
