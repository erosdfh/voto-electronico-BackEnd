package com.votoElectronico.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.votoElectronico.beans.response.MakeValidateConectivity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("/validate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ValidateConnectivity {

    @GetMapping(value = "/connection", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> DisabledCodeAppointmentPOSU(HttpServletRequest request, HttpServletResponse response){

        MakeValidateConectivity responseRequest =  new MakeValidateConectivity();
        //responseRequest.ok("ok");

        return ResponseEntity.ok(responseRequest);

    }
}
