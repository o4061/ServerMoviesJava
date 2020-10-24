package com.example;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/voteServlet")

public class voteServlet extends HttpServlet {

    private String getPartValue(Part part) throws IOException {
        if (part == null) return null;
        return new String(part.getInputStream().readAllBytes());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Initialize.getConnection();
            Gson gson = new Gson();
            Vote newVote = new Vote();

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");

            newVote.setUser_id(Integer.parseInt(request.getParameter("userId")));
            newVote.setMovie_id(Integer.parseInt(request.getParameter("movieId")));
            newVote.setScore(Integer.parseInt(request.getParameter("score")));

            if (newVote.getMovie_id() < 0 || newVote.getUser_id() < 0 || newVote.getScore() < 0 || newVote.getScore() > 10) {
                out.print(gson.toJson("wrong request"));
                response.setStatus(400);
                return;

            }

            ArrayList<Vote> votes = Selection.getVotesByUserId(con, newVote.getUser_id());

            if (votes.isEmpty()) {
                Insertion.addVote(con, newVote);
                out.print(gson.toJson(newVote));
                response.setStatus(200);
            } else {
                for (Vote item : votes) {
                    if (item.getMovie_id() == newVote.getMovie_id()) {
                        Update.changeVote(con, newVote);
                        out.print(gson.toJson(newVote));
                        response.setStatus(200);
                        return;
                    }
                }
                Insertion.addVote(con, newVote);
                out.print(gson.toJson(newVote));
                response.setStatus(200);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Initialize.getConnection();
            Gson gson = new Gson();

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");

            if (request.getParameterMap().containsKey("user_id")) {
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                if (user_id > 0) {
                    ArrayList<Vote> votes = Selection.getVotesByUserId(con, user_id);

                    if (!votes.isEmpty()) {
                        response.setStatus(200);
                        out.print(gson.toJson(votes));
                    } else {
                        response.setStatus(400);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
