package com.example.testingapi.model;

import javax.persistence.*;


@Entity
@Table(name = "user_movies")
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String poster;
    private String title;
    private String type;
    private String year;
    private String imdbID;


    private String userID;
    private String rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbid) {
        this.imdbID = imdbid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String user_id) {
        this.userID = user_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
