package com.example;

public class Vote {
    private int movie_id;
    private int user_id;
    private int score;

    public Vote() {

    }

    public Vote(int movie_id, int user_id, int score) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.score = score;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "movie_id=" + movie_id +
                ", user_id=" + user_id +
                ", score=" + score +
                '}';
    }
}
