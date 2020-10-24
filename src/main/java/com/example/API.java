package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Integer.parseInt;

public class API {

    private static HttpURLConnection connection;
    private static String apiKey = new String("?api_key=77e53ce7603447d4d1b677e478a9b302");
    private static String base_url = new String("https://api.themoviedb.org/3/movie/");
    private static String language = new String("&language=en-US");

    static JSONObject downloadFromUrl(String urlPath) throws IOException, ParseException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        //take url
        URL url = new URL(urlPath);
        //set the connection
        connection = (HttpURLConnection) url.openConnection();
        //set the method
        connection.setRequestMethod("GET");
        //set the connect timeout (5 sec)
        connection.setConnectTimeout(5000);
        //set the read timeout (5 sec)
        connection.setReadTimeout(5000);
        //get the status
        int status = connection.getResponseCode();
        JSONObject obj = null;

        if (199 < status && status < 300) {

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(responseContent.toString());
        }

        return obj;
    }

    static String getPopularMovies(int pageNum) throws IOException, ParseException, SQLException {

        Connection connection = Initialize.getConnection();

        JSONObject popularMovies = downloadFromUrl(base_url + "popular" + apiKey + language + "&page=" + pageNum);
        String totalPages = popularMovies.get("total_pages").toString();
        String total_results = popularMovies.get("total_results").toString();
        int index = parseInt(total_results) / parseInt(totalPages);

        for (int i = 0; i < index; i++) {
            Movie movie = new Movie();
            JSONArray array = (JSONArray) popularMovies.get("results");

            JSONObject obj = (JSONObject) array.get(i);
            if (obj.size() != 14) {
                continue;
            }
            movie.setPopularity(Double.parseDouble(obj.get("popularity").toString()));
            movie.setTmdb_id(Integer.parseInt(obj.get("id").toString()));
            movie.setTitle(obj.get("title").toString().replace("'", "|"));
            movie.setOverview(obj.get("overview").toString().replace("'", "|"));
            if (obj.get("release_date") != null) {
                Date date = Date.valueOf(obj.get("release_date").toString());
                movie.setRelease_date(date);
            }
            if (obj.get("poster_path") != null) {
                movie.setPoster(obj.get("poster_path").toString());
            }
            movie.setVote_count(Integer.parseInt(obj.get("vote_count").toString()));
            movie.setVote_average(Double.parseDouble(obj.get("vote_average").toString()));

            JSONObject movieDetails = downloadFromUrl(base_url + obj.get("id") + apiKey + language);
            if (movieDetails.get("runtime") != null) {
                movie.setRuntime(Integer.parseInt(movieDetails.get("runtime").toString()));
            }

            JSONObject movieVideos = downloadFromUrl(base_url + obj.get("id") + "/videos" + apiKey + language);
            JSONArray videoArray = (JSONArray) movieVideos.get("results");
            if (videoArray.toString().equals("[]")) {
                movie.setTrailer("empty");
            } else {
                JSONObject key = (JSONObject) videoArray.get(0);
                if (key.get("key") != null) {
                    movie.setTrailer(key.get("key").toString());
                }
            }
            Insertion.addMovie(connection, movie);

        }
        return "page " + pageNum + " download compete";
    }

    public static void main(String[] args) throws ParseException, SQLException, IOException, ClassNotFoundException {

        Connection connection = Initialize.getConnection();
        connection.close();
    }
}
