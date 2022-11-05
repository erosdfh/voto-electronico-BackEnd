

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
import com.votoElectronico.data.Login;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.alumnoDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.data.resultLoginAlumnoDto;
import com.votoElectronico.logic.AlumnoLogic;
import com.votoElectronico.logic.CandidatoLogic;
import com.votoElectronico.logic.UsuarioLogic;
import com.votoElectronico.logic.eleccionesLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/votos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class votoElectronico {
    private static final Logger log = LoggerFactory.getLogger(AlumnosController.class);
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
    eleccionesLogic elecciones;


  
    @GetMapping(value = "/elecciones", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUsuario(HttpServletRequest request, HttpServletResponse response, int idCandidato, int idAlumno) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = elecciones.elecciones(cred, idCandidato,idAlumno);
                return ResponseEntity.ok(responseRequest);

            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
 

    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody Login dato) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<resultLoginAlumnoDto> responseRequest = elecciones.login(cred, dato);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    
    @GetMapping(value = "/conteo_alumnos", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> conteoAlumnos(HttpServletRequest request, HttpServletResponse response, int voto) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<Integer> responseRequest = elecciones.conteoAlumnos(cred, voto);
                return ResponseEntity.ok(responseRequest);

            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }

    
}