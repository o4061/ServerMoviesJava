package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public static void changeVote(Connection connection, Vote vote) throws SQLException {
        String sql =
                "UPDATE VOTES\n" +
                        "SET score=" + vote.getScore() + "\n" +
                        "WHERE USER_ID=" + vote.getUser_id() + " " +
                        "AND MOVIE_ID=" + vote.getMovie_id();

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public static void changeUserInfos(Connection connection, User user) throws SQLException {
        String sql =
                "UPDATE USERS\n" +
                        "SET FNAME='" + user.getFname() + "'," +
                        "LNAME='" + user.getLname() + "'," +
                        "AGE=" + user.getAge() + "," +
                        "PASSWORD='" + user.getPassword() + "'\n" +
                        "WHERE USER_ID=" + user.getUser_id();

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
