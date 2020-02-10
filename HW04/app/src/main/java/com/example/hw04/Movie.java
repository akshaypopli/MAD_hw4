package com.example.hw04;

import java.io.Serializable;

public class Movie implements Serializable {
    String name;
    String description;
    String genre;
    int rating;
    int year;
    String imdb;


    public Movie(String name, String description, String genre, int rating, int year, String imdb) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", year='" + year + '\'' +
                ", imdb='" + imdb + '\'' +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
