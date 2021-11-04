package com.example.testingapi.controller;


import com.example.testingapi.model.Movie;
import com.example.testingapi.model.User;
import com.example.testingapi.model.UserMovie;
import com.example.testingapi.repository.UserMovieRepository;
import com.example.testingapi.service.MovieAPIService;
import com.example.testingapi.service.UserMovieService;
import com.example.testingapi.service.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MovieController {


    @Autowired
    private MovieAPIService movieAPIService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private UserMovieService userMovieService;




    @GetMapping("/index")
    public String postMovies(Movie movie,Model model) throws UnirestException, UnsupportedEncodingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        model.addAttribute("user", user.getUserName());
        model.addAttribute("userMovies", userMovieRepository.findAllByUserID(user.getId().toString()));
        List<Movie> movieList = movieAPIService.listMovies();
        model.addAttribute("movies", movieList);
        return "index";
    }

    @PostMapping("/index")
    public String postMovies(@Valid Movie movie, BindingResult bindingResult, Model model) throws UnirestException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "index";
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Movie> movieList = movieAPIService.listMovies();
        model.addAttribute("user", user.getUserName());

        UserMovie userMovie = new UserMovie();

        userMovie.setUserID(String.valueOf(user.getId()));
        userMovie.setTitle(movie.getTitle());
        userMovie.setYear(movie.getYear());
        userMovie.setType(movie.getType());
        userMovie.setRating(movie.getRating());
        userMovie.setPoster(movie.getPoster());
        userMovie.setImdbID(movie.getImdbID());


        userMovieRepository.save(userMovie);
        model.addAttribute("movies", movieList);
        return "redirect:index";
    }


    @GetMapping("/index/search")
    public String searchMovie(@RequestParam(value = "title") String keyword,@Valid Movie movie, BindingResult result, Model model) throws UnirestException, UnsupportedEncodingException {
        if(result.hasErrors()) {
            return "index";
        }
        model.addAttribute("movie", movie);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user.getUserName());
        model.addAttribute("userMovies", userMovieRepository.findAllByUserID(user.getId().toString()));

        model.addAttribute("user", user.getUserName());
        MovieAPIService movieService = new MovieAPIService();
        movieService.setName(keyword);
        List<Movie> foundMovies = movieService.searchMovies(keyword);
        if(foundMovies.isEmpty()) {
            return "index";
        }
        model.addAttribute("foundMovies", foundMovies);
        return "indexs";
    }

    @PostMapping("/index/search")
    public String postMoviesSearch(@Valid Movie movie, BindingResult bindingResult, Model model) throws UnirestException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "index";
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Movie> movieList = movieAPIService.listMovies();

        model.addAttribute("user", user.getUserName());
        UserMovie userMovie = new UserMovie();

        userMovie.setUserID(String.valueOf(user.getId()));
        userMovie.setTitle(movie.getTitle());
        userMovie.setYear(movie.getYear());
        userMovie.setType(movie.getType());
        userMovie.setRating(movie.getRating());
        userMovie.setPoster(movie.getPoster());
        userMovie.setImdbID(movie.getImdbID());


        userMovieRepository.save(userMovie);
        model.addAttribute("movies", movieList);
        return "redirect:index";
    }




}
