package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public void removeFromFavorites(Connection connection, Favorite favorite) throws SQLException {
        String sql =
                "DELETE FROM FAVORITES\n"+
                "WHERE USER_ID=" + favorite.getUser_id() + " " +
                "AND MOVIE_ID=" + favorite.getMovie_id();

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public void deleteVote(Connection connection, Vote vote) throws SQLException {
        String sql =
                "DELETE FROM VOTES\n"+
                        "WHERE USER_ID=" + vote.getUser_id() + " " +
                        "AND MOVIE_ID=" + vote.getMovie_id();

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
