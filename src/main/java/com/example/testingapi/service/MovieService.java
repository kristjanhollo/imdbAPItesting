package com.example.testingapi.service;

import com.example.testingapi.model.Movie;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {


    private String name = "Die Hard";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private final String HOST = "https://movie-database-imdb-alternative.p.rapidapi.com/";
    private final String CHARSET = "UTF-8";
    private final String API_HOST = "movie-database-imdb-alternative.p.rapidapi.com";
    private final String API_KEY = "e23d4a42c3mshe1feb667b531871p1162a6jsn67cd66488bfb";
    private final String QUERY = String.format("s=%s&r=json&type=movie&page=1", URLEncoder.encode(this.name, CHARSET));

    public MovieService() throws UnsupportedEncodingException {
    }


    private String getMovieName(String name) {
        return name;
    }


    public List<Movie> listMovies() throws UnirestException, UnsupportedEncodingException {
        HttpResponse<JsonNode> getJson = apiRequest(HOST,QUERY,API_HOST, API_KEY);
        JSONArray jsonArray = jsonArrayFromJsonObject(getJson);
        return fetchMoviesFromJsonArray(jsonArray);
    }

    public List<Movie> searchMovies(String title) throws UnirestException, UnsupportedEncodingException {
        String QUERY = String.format("s=%s&r=json&type=movie&page=1", URLEncoder.encode(title, CHARSET));
        HttpResponse<JsonNode> getJson = apiRequest(HOST,QUERY,API_HOST, API_KEY);
        JSONArray jsonArray = jsonArrayFromJsonObject(getJson);
        return fetchMoviesFromJsonArray(jsonArray);
    }


    private HttpResponse<JsonNode> apiRequest(String HOST, String QUERY, String API_HOST, String API_KEY) throws UnirestException {
        return Unirest.get(HOST + "?" + QUERY)
                .header("x-rapidapi-host", API_HOST)
                .header("x-rapidapi-key", API_KEY)
                .asJson();
    }

    private JSONArray jsonArrayFromJsonObject(HttpResponse<JsonNode> response) throws UnirestException, UnsupportedEncodingException {
        final JSONObject obj = new JSONObject(response.getBody().toString());
        if(obj.toString().contains("Error")) {
            return new JSONArray();
        }
        return obj.getJSONArray("Search");
    }


    private List<Movie> fetchMoviesFromJsonArray(JSONArray jsonArray) {
        final int jsonArrayLength = jsonArray.length();

        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < jsonArrayLength; ++i) {
            final JSONObject movie = jsonArray.getJSONObject(i);

            Movie movieToList = new Movie();
            movieToList.setTitle(movie.getString("Title"));
            movieToList.setYear(movie.getString("Year"));
            movieToList.setImdbID(movie.getString("imdbID"));
            movieToList.setPoster(movie.getString("Poster"));
            movieList.add(movieToList);
        }

        return movieList;
    }

}