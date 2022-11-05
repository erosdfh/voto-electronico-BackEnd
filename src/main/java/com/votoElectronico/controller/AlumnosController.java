package com.votoElectronico.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.alumnoDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.logic.AlumnoLogic;
import com.votoElectronico.logic.CandidatoLogic;
import com.votoElectronico.logic.UsuarioLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/alumno")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlumnosController {
    private static final Logger log = LoggerFactory.getLogger(votoElectronico.class);
    private final String tokenPosu = "basic f7154165-c64f-4316-b5c8-e76cb9348832";
    private final String tokenGenesys = "basic b0137a61-ce2c-4f15-b66b-26d52cca7d5d";


    @Value("${gcp.gcpcupon.jdbc-url}")
    private String url;

    @Value("${gcp.gcpcupon.username}")
    private String username;

    @Value("${gcp.gcpcupon.password}")
    private String password;

    @Value("${gcp.gcpcupon.connectionTimeout}")
    private Integer timeout;

    @Value("${gcp.gcpcupon.readOnly}")
    private boolean read_only;

    @Value("${gcp.gcpcupon.maximunPoolSize}")
    private Integer maximunPoolSize;

    @Value("${gcp.gcpcupon.poolName}")
    private String pool_name;


    @Value("${url.admin.aliviamed}")
    private String url_alivia;
    
    @Autowired
    AlumnoLogic alumno;


  
    @PostMapping(value = "/registrarAlumno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody List<alumnoDto> dato) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = alumno.createAlumno(cred, dato);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    
    @PostMapping(value = "/actualizarAlumno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody List<alumnoDto> dato) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = alumno.updateAlumno(cred, dato);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }

    @GetMapping(value = "/eliminarAlumno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUsuario(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = alumno.deleteAlumno(cred, id);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    @GetMapping(value = "/listarAlumno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listUsuario(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<alumnoDto> responseRequest = alumno.listarAlumno(cred, id);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    

    
}