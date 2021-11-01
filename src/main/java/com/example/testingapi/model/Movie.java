package com.example.testingapi.model;

import java.util.Objects;

public class Movie {

    private String Title;
    private String Year;
    private String Poster;
    private String imdbID;
    private String Type;


    public Movie() {
    }

    public Movie(String title, String year, String poster, String imdbID, String type) {
        Title = title;
        Year = year;
        Poster = poster;
        this.imdbID = imdbID;
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Poster='" + Poster + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
