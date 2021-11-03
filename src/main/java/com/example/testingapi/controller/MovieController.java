package com.example.testingapi.controller;


import com.example.testingapi.model.Movie;
import com.example.testingapi.model.User;
import com.example.testingapi.service.MovieAPIService;
import com.example.testingapi.service.MovieService;
import com.example.testingapi.service.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

@Controller
public class MovieController {


    @Autowired
    private MovieAPIService movieAPIService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String showSearch(Model model) throws UnirestException, UnsupportedEncodingException {
        List<Movie> movies = movieAPIService.listMovies();
        model.addAttribute("movies", movies);
        return "get-movies";
    }



    @GetMapping("/index")
    public String postMovies(Model model) throws UnirestException, UnsupportedEncodingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userMovies", movieService.listMoviesByUser(user.getId()));
        List<Movie> movieList = movieAPIService.listMovies();
        model.addAttribute("movies", movieList);
        return "index";
    }

    @GetMapping("/index/search")
    public String searchMovie(@RequestParam(value = "title") String keyword, Model model) throws UnirestException, UnsupportedEncodingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userMovies", movieService.listMoviesByUser(user.getId()));


        MovieAPIService movieService = new MovieAPIService();
        movieService.setName(keyword);
        List<Movie> foundMovies = movieService.searchMovies(keyword);
        if(foundMovies.isEmpty()) {
            return "index";
        }
        model.addAttribute("foundMovies", foundMovies);
        return "indexs";
    }






}
