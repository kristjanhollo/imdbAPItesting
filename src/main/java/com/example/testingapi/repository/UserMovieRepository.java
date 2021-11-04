package com.example.testingapi.repository;

import com.example.testingapi.model.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {

    List<UserMovie> findAllByUserID(String userID);
}
