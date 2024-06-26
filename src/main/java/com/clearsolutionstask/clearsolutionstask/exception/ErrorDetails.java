package com.clearsolutionstask.clearsolutionstask.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private HttpStatus status;

}
