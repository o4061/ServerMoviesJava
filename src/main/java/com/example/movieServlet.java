package com.example;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/movieServlet")
public class movieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Initialize.getConnection();
            Gson gson = new Gson();

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");

            Movie movie = null;

            if (request.getParameterMap().containsKey("movie_id")) {
                int movie_id = Integer.parseInt(request.getParameter("movie_id"));
                if (0 < movie_id && movie_id <= 1998) {
                    movie = Selection.getMovieById(con, movie_id);
                    if (movie != null) {
                        out.print(gson.toJson(movie));
                        response.setStatus(200);
                    }
                } else {
                    response.setStatus(400);
                }
            }

            if (request.getParameterMap().containsKey("part")) {
                int part = Integer.parseInt(request.getParameter("part"));
                ArrayList<Movie> movies = new ArrayList<Movie>();
                if (0 <= part && part <= 18) {
                    for (int i = part * 100; i < part * 100 + 100; i++) {
                        movie = Selection.getMovieById(con, i);
                        movies.add(movie);
                    }

                    if (!movies.isEmpty()) {
                        out.print(gson.toJson(movies));
                        response.setStatus(200);
                    }
                } else {
                    response.setStatus(400);
                }
            }

            if (request.getParameterMap().containsKey("title")) {
                String title = request.getParameter("title");

                movie = Selection.getMovieByTitle(con, title);
                if (movie != null) {
                    out.print(gson.toJson(movie));
                    response.setStatus(200);
                }
            } else {
                response.setStatus(400);
            }

            if (request.getParameterMap().isEmpty()) {
                ArrayList<Movie> movies = new ArrayList<Movie>();
                for (int i = 1; i < 10; i++) {
                    movie = Selection.getMovieById(con, i);
                    movies.add(movie);
                }

                response.setStatus(200);
                out.print(gson.toJson(movies));
                out.flush();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
