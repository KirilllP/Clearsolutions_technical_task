package com.clearsolutionstask.clearsolutionstask.dto;

import com.clearsolutionstask.clearsolutionstask.validator.oldEnough.OldEnough;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUserDto {


    @Email(message = "This is not a valid email format")
    String email;


    String firstName;


    String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @OldEnough
    LocalDate birthDate;

    String address;

    String phoneNumber;
}
