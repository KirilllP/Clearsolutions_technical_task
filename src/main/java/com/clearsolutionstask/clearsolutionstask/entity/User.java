package com.clearsolutionstask.clearsolutionstask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String email;

    String firstName;

    String lastName;

    LocalDate birthDate;

    String address;

    String phoneNumber;

}
