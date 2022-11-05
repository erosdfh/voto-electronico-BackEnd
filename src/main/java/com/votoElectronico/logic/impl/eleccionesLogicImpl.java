package com.votoElectronico.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.beans.response.UserNotFoundException;
import com.votoElectronico.dao.CandidatoDao;
import com.votoElectronico.dao.Conexion;
import com.votoElectronico.dao.EleccionesDao;
import com.votoElectronico.dao.usuarioDao;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.Login;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.data.resultLoginAlumnoDto;
import com.votoElectronico.logic.UsuarioLogic;
import com.votoElectronico.logic.eleccionesLogic;

import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class eleccionesLogicImpl implements eleccionesLogic {
	
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
    
    @Value("${prescript-signature}")
    private String url_prescript_signature;
    
    private static final Logger log = LoggerFactory.getLogger(eleccionesLogicImpl.class);
    
        
    Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);

   @Override
    public ResponseEntity<ApiError> elecciones (Credenciales cred, int idCandidato, int idAlumno) throws UserNotFoundException {
    	ApiError response = new ApiError();
    	String resp = "";
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            EleccionesDao dao =  new EleccionesDao(conn);
            	resp = dao.votocandidato(idCandidato);
            	resp = dao.votoAlumno(idAlumno);
            
           if(!resp.isEmpty()) {
        	   response.setMessage("Success");
        	   response.setStatus(true);
        	   response.setInstant(Instant.now());
           }else {
        	   response.setMessage("Ocurrio un Error");
        	   response.setStatus(false);
        	   response.setInstant(Instant.now());
           }

       }catch (Exception ex){
            System.out.println("error" + ex.getMessage());
       }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
   
   @Override
   public pageResponse<resultLoginAlumnoDto> login (Credenciales cred, Login data) throws UserNotFoundException {
	   	pageResponse<resultLoginAlumnoDto> respons = new pageResponse<>();
   		List<resultLoginAlumnoDto> resp;
       Connection conn = null;
       try {
           conn = Conexion.getConexion(cred);
           EleccionesDao dao =  new EleccionesDao(conn);
           resp = dao.login(data);
           
           	if(!resp.isEmpty()) {
           		List<resultLoginAlumnoDto> respuesta = new ArrayList<resultLoginAlumnoDto>();
           		respuesta=resp;
         	   respons.setStatus(true);
         	   respons.setTotalItems(0);
         	   respons.setItems(respuesta);
            }else {
         	   respons.setStatus(false);
         	   respons.setTotalItems(0);
         	  respons.setItems(resp);
            }

      }catch (Exception ex){
           System.out.println("error" + ex.getMessage());
      }

       return respons;
   }
   
   public pageResponse<Integer> conteoAlumnos (Credenciales cred, Integer voto) throws UserNotFoundException{
	   pageResponse<Integer> respons = new pageResponse<>();
  		List<Integer> resp;
      Connection conn = null;
      try {
          conn = Conexion.getConexion(cred);
          EleccionesDao dao =  new EleccionesDao(conn);
          resp = dao.conteoAlumnos(voto);
          
          	if(!resp.isEmpty()) {
        	   respons.setStatus(true);
        	   respons.setTotalItems(0);
        	   respons.setItems(resp);
           }else {
        	   respons.setStatus(false);
        	   respons.setTotalItems(0);
        	  respons.setItems(resp);
           }

     }catch (Exception ex){
          System.out.println("error" + ex.getMessage());
     }

      return respons;
  
   }
   
    public ApiError createApiError(WebRequest request) {
    	ApiError apiError= new ApiError();
    	apiError.setInstant(Instant.now());
    	apiError.setPath(request.getContextPath());
    	return apiError;
    }


}
