package com.example;

import java.sql.Date;

public class Movie {
    private int movie_id;
    private int tmdb_id;
    private String title;
    private String overview;
    private Date release_date;
    private int runtime;
    private String trailer;
    private String poster;
    private double vote_average;
    private int vote_count;
    private double popularity;

    public Movie(){

    }

    public Movie(int tmdb_id, String title, String overview, Date release_date,
                 int runtime, String trailer, String poster, double vote_average,
                 int vote_count, double popularity) {
        
        this.tmdb_id = tmdb_id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.runtime = runtime;
        this.trailer = trailer;
        this.poster = poster;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.popularity = popularity;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(int tmdb_id) {
        this.tmdb_id = tmdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", tmdb_id=" + tmdb_id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date=" + release_date +
                ", runtime=" + runtime +
                ", trailer='" + trailer + '\'' +
                ", poster='" + poster + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", popularity=" + popularity +
                '}';
    }
}
