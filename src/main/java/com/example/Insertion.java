package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insertion {

    public static void addMovie(Connection con, Movie movie) throws SQLException {
        String sql =
                "INSERT INTO MOVIES " +
                        "(TMDB_ID,TITLE,OVERVIEW,RELEASE_DATE,RUNTIME,TRAILER,POSTER,VOTE_AVERAGE,VOTE_COUNT,POPULARITY)\n" +
                        "VALUES( " +
                        movie.getTmdb_id() + ',' +
                        "\'" + movie.getTitle() + "\'" + ',' +
                        "\'" + movie.getOverview() + "\'" + ',' +
                        "STR_TO_DATE(\'" + movie.getRelease_date().toString() + "\',\'%Y-%m-%d\')" + ',' +
                        movie.getRuntime() + ',' +
                        "\'" + movie.getTrailer() + "\'" + ',' +
                        "\'" + movie.getPoster() + "\'" + ',' +
                        movie.getVote_average() + ',' +
                        movie.getVote_count() + ',' +
                        movie.getPopularity() +
                        ')';
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public static void addUser(Connection con, User user) throws SQLException {
        String sql =
                "INSERT INTO USERS " +
                        "(FNAME,LNAME,AGE,EMAIL,PASSWORD)\n" +
                        "VALUES( " +
                        "\'" + user.getFname() + "\'" + ',' +
                        "\'" + user.getLname() + "\'" + ',' +
                        user.getAge() + ',' +
                        "\'" + user.getEmail() + "\'" + ',' +
                        "\'" + user.getPassword() + "\'" +
                        ')';
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public static void addVote(Connection con, Vote vote) throws SQLException {
        String sql =
                "INSERT INTO VOTES " +
                        "(MOVIE_ID,USER_ID,SCORE)\n" +
                        "VALUES( " +
                        vote.getMovie_id() + ',' +
                        vote.getUser_id() + ',' +
                        vote.getScore() +
                        ')';
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public static void addFavorite(Connection con, Favorite favorite) throws SQLException {
        String sql =
                "INSERT INTO FAVORITES " +
                        "(MOVIE_ID,USER_ID)\n" +
                        "VALUES( " +
                        favorite.getMovie_id() + ',' +
                        favorite.getUser_id() +
                        ')';
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }
}