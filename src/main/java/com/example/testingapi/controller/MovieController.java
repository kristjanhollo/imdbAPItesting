package com.example.testingapi.controller;


import com.example.testingapi.model.Movie;
import com.example.testingapi.service.MovieService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MovieController {


    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String showSearch(@RequestParam(required = false) String search, Model model) throws UnirestException {
        List<Movie> movies = movieService.listMovies(search);
        model.addAttribute("movies", movies);
        return "get-movies";
    }




}
