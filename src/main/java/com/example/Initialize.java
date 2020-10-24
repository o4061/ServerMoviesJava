package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Initialize {

    public static Connection getConnection() throws SQLException {
        String url = new String("jdbc:mysql://localhost:");
        String NameDB = new String("/test");
        String Encode = new String("?characterEncoding=UTF-8");
        String user = new String("root");
        String password = new String("");
        int port = 3306;

        Connection con = DriverManager.getConnection(url + port + NameDB + Encode, user, password);

        return con;
    }

    public static void CreateTables() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = getConnection();

        String createMovies = new String(
                "CREATE TABLE MOVIES(" +
                        "MOVIE_ID int NOT NULL AUTO_INCREMENT," +
                        "TMDB_ID int," +
                        "TITLE varchar(100)," +
                        "OVERVIEW varchar(1400)," +
                        "RELEASE_DATE date," +
                        "RUNTIME int," +
                        "TRAILER varchar(30)," +
                        "POSTER varchar(100)," +
                        "VOTE_AVERAGE double," +
                        "VOTE_COUNT int," +
                        "POPULARITY double," +

                        "PRIMARY KEY(MOVIE_ID)" +
                        ")");

        String createUsers = new String(
                "CREATE TABLE USERS(" +
                        "USER_ID int NOT NULL AUTO_INCREMENT," +
                        "FNAME varchar(10)," +
                        "LNAME varchar(10)," +
                        "AGE int," +
                        "EMAIL varchar(25)," +
                        "PASSWORD varchar(25)," +

                        "PRIMARY KEY(USER_ID)" +
                        ")");

        String createFavorites = new String(
                "CREATE TABLE FAVORITES(" +
                        "MOVIE_ID int," +
                        "USER_ID int" +
                        ")");

        String createVotes = new String(
                "CREATE TABLE VOTES(" +
                        "MOVIE_ID int," +
                        "USER_ID int," +
                        "SCORE int" +
                        ")");

        Statement statement = con.createStatement();
        statement.executeUpdate(createMovies);
        statement.executeUpdate(createUsers);
        statement.executeUpdate(createFavorites);
        statement.executeUpdate(createVotes);
    }
}