package com.clearsolutionstask.clearsolutionstask.dto;

import com.clearsolutionstask.clearsolutionstask.validator.fromToDates.FromToDates;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FromToDates
public class DateRangeDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "birth date name is NULL")
    @Past(message = "\"From\" must be in the past")
    private LocalDate from;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "birth date name is NULL")
    @Past(message = "\"To\" must be in the past")
    private LocalDate to;

}
