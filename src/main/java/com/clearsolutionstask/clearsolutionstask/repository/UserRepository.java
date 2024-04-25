package com.clearsolutionstask.clearsolutionstask.repository;


import com.clearsolutionstask.clearsolutionstask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
