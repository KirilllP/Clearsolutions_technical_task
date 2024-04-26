package com.clearsolutionstask.clearsolutionstask.repository;


import com.clearsolutionstask.clearsolutionstask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByBirthDateBetween(LocalDate from, LocalDate to);

    Long deleteAllById(Integer id);

}
