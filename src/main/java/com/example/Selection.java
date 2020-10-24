package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Selection {

    public static Movie getMovieById(Connection con, int movie_id) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM MOVIES\n" +
                        "WHERE MOVIE_ID=" + movie_id;

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Movie movie = new Movie();

        while (resultSet.next()) {
            movie.setMovie_id(resultSet.getInt("MOVIE_ID"));
            movie.setTmdb_id(resultSet.getInt("TMDB_ID"));
            movie.setTitle(resultSet.getString("TITLE"));
            movie.setOverview(resultSet.getString("OVERVIEW"));
            movie.setRelease_date(resultSet.getDate("RELEASE_DATE"));
            movie.setRuntime(resultSet.getInt("RUNTIME"));
            movie.setTrailer(resultSet.getString("TRAILER"));
            movie.setPoster(resultSet.getString("POSTER"));
            movie.setVote_average(resultSet.getDouble("VOTE_AVERAGE"));
            movie.setVote_count(resultSet.getInt("VOTE_COUNT"));
            movie.setPopularity(resultSet.getDouble("POPULARITY"));
        }
        return movie;
    }


    public static Movie getMovieByTitle(Connection con, String movie_title) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM MOVIES\n" +
                        "WHERE TITLE=" + "'" + movie_title + "'";

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Movie movie = new Movie();

        while (resultSet.next()) {
            movie.setMovie_id(resultSet.getInt("MOVIE_ID"));
            movie.setTmdb_id(resultSet.getInt("TMDB_ID"));
            movie.setTitle(resultSet.getString("TITLE"));
            movie.setOverview(resultSet.getString("OVERVIEW"));
            movie.setRelease_date(resultSet.getDate("RELEASE_DATE"));
            movie.setRuntime(resultSet.getInt("RUNTIME"));
            movie.setTrailer(resultSet.getString("TRAILER"));
            movie.setPoster(resultSet.getString("POSTER"));
            movie.setVote_average(resultSet.getDouble("VOTE_AVERAGE"));
            movie.setVote_count(resultSet.getInt("VOTE_COUNT"));
            movie.setPopularity(resultSet.getDouble("POPULARITY"));
        }
        return movie;
    }

    public static User getUserById(Connection con, int user_id) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM USERS\n" +
                        "WHERE USER_ID=" + user_id;

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        User user = new User();

        while (resultSet.next()) {
            user.setUser_id(resultSet.getInt("USER_ID"));
            user.setFname(resultSet.getString("FNAME"));
            user.setLname(resultSet.getString("LNAME"));
            user.setAge(resultSet.getInt("AGE"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setPassword(resultSet.getString("PASSWORD"));
        }
        return user;
    }

    public static Vote getVoteByMovieId(Connection con, int movie_id) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM VOTES\n" +
                        "WHERE MOVIE_ID=" + movie_id;

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Vote vote = new Vote();

        while (resultSet.next()) {
            vote.setMovie_id(resultSet.getInt("MOVIE_ID"));
            vote.setUser_id(resultSet.getInt("USER_ID"));
            vote.setScore(resultSet.getInt("SCORE"));
        }
        return vote;
    }


    public static ArrayList<Vote> getVotesByUserId(Connection con, int user_id) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM VOTES\n" +
                        "WHERE USER_ID=" + user_id;

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);


        ArrayList<Vote> votes = new ArrayList<Vote>();

        while (resultSet.next()) {
            Vote vote = new Vote(resultSet.getInt("MOVIE_ID"),
                    resultSet.getInt("USER_ID"),
                    resultSet.getInt("SCORE"));
            votes.add(vote);
        }
        return votes;
    }

    public static ArrayList<Favorite> getFavorites(Connection con, int user_id) throws SQLException {
        String sql =
                "SELECT * " +
                        "FROM FAVORITES\n" +
                        "WHERE USER_ID=" + user_id;

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Favorite> favorites = new ArrayList<Favorite>();


        while (resultSet.next()) {
            Favorite favorite = new Favorite();
            favorite.setMovie_id(resultSet.getInt("MOVIE_ID"));
            favorite.setUser_id(resultSet.getInt("USER_ID"));

            favorites.add(favorite);

        }
        return favorites;
    }
}