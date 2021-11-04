package com.example.testingapi.service;

import com.example.testingapi.model.UserMovie;
import com.example.testingapi.repository.UserMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMovieService {

    private UserMovieRepository userMovieRepository;

    public void saveMovieToUserTable(UserMovie userMovie) {
        if (userMovie.getImdbID() != null) {
            userMovieRepository.save(userMovie);
        }
    }




}
