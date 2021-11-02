package com.example.testingapi.service;

import com.example.testingapi.model.Movie;
import com.example.testingapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> listMoviesByUser(int id) {
        return movieRepository.findAllByUserId(id);
    }
}
