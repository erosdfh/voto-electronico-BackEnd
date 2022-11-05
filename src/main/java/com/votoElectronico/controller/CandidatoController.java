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
import com.votoElectronico.data.CandidatoGanadorDTO;
import com.votoElectronico.data.ReporteEleccionGeneralDTO;
import com.votoElectronico.data.ReporteEleccionGradoDTO;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.logic.CandidatoLogic;
import com.votoElectronico.logic.UsuarioLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/candidato")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CandidatoController {
    private static final Logger log = LoggerFactory.getLogger(CandidatoController.class);
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
    CandidatoLogic candidato;


  
    @PostMapping(value = "/registrarCandidato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody CandidatoDto dato) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = candidato.createCandidato(cred, dato);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    
    @PostMapping(value = "/actualizarCandidato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody CandidatoDto dato) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = candidato.updateCandidato(cred, dato);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
    //    }
       // return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            
    }
    @GetMapping(value = "/eliminarCandidato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUsuario(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                ResponseEntity<ApiError> responseRequest = candidato.deleteCandidato(cred, id);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }

    @GetMapping(value = "/listarCandidato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listUsuario(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<CandidatoDto> responseRequest = candidato.listarCandidato(cred, id);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
            
    }
    @GetMapping(value = "/listarGanador", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listGanador(HttpServletRequest request, HttpServletResponse response) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<CandidatoGanadorDTO> responseRequest = candidato.listGanador(cred);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
    }
    @GetMapping(value = "/listarEleccionPorGrado", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> lisEleccionPorGrado(HttpServletRequest request, HttpServletResponse response, Integer grado) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
              
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<ReporteEleccionGradoDTO> responseRequest = candidato.lisEleccionPorGrado(cred,grado);

                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
    }
      
    @PostMapping(value = "/listarEleccionGeneral", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listEleccionGeneral(HttpServletRequest request, HttpServletResponse response, @RequestBody ReporteEleccionGeneralDTO data) {
        String requestTokenHeader = request.getHeader("Authorization");
            try {
            	System.out.println("dataEntrada:  "+ data);
                Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);
                pageResponse<ReporteEleccionGeneralDTO> responseRequest = candidato.listEleccionGeneral(cred, data);
                return ResponseEntity.ok(responseRequest);


            } catch (Exception e) {
                log.error("Error general: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<Map<String, Object>>(HttpStatus.UNAUTHORIZED);
            }
    }
    
}