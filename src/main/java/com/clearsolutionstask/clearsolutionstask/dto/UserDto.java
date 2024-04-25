package com.clearsolutionstask.clearsolutionstask.dto;

import com.clearsolutionstask.clearsolutionstask.validator.oldEnough.OldEnough;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Null
    Integer id;

    @NotNull(message = "Email is NULL")
    @Email(message = "This is not a valid email format")
    String email;

    @NotNull(message = "first name is NULL")
    String firstName;

    @NotNull(message = "last name is NULL")
    String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "birth date name is NULL")
    @OldEnough
    LocalDate birthDate;

    String address;

    String phoneNumber;
}
