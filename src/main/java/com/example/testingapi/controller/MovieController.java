package com.example.testingapi.controller;


import com.example.testingapi.model.Movie;
import com.example.testingapi.service.MovieService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MovieController {


    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String showSearch(Model model) throws UnirestException, UnsupportedEncodingException {
        List<Movie> movies = movieService.listMovies();
        model.addAttribute("movies", movies);
        return "get-movies";
    }



    @GetMapping("/index")
    public String postMovies(Model model) throws UnirestException, UnsupportedEncodingException {
        List<Movie> movieList = movieService.listMovies();
        model.addAttribute("movies", movieList);
        return "index";
    }

    @GetMapping("/index/search")
    public String searchMovie(@RequestParam(value = "title") String keyword, Model model) throws UnirestException, UnsupportedEncodingException {

        MovieService movieService = new MovieService();
        movieService.setName(keyword);
        List<Movie> foundMovies = movieService.searchMovies(keyword);
        if(foundMovies.isEmpty()) {
            return "index";
        }
        model.addAttribute("foundMovies", foundMovies);
        return "indexs";


    }




}
