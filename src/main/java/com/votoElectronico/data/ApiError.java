package com.votoElectronico.data;


import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
private boolean status;
private String path;
private String message;
private Instant instant;

}
