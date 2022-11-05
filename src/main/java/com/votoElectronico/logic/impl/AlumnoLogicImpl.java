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
import com.votoElectronico.dao.AlumnoDao;
import com.votoElectronico.dao.CandidatoDao;
import com.votoElectronico.dao.Conexion;
import com.votoElectronico.dao.usuarioDao;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.RegidorDto;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.alumnoDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.logic.AlumnoLogic;
import com.votoElectronico.logic.CandidatoLogic;
import com.votoElectronico.logic.UsuarioLogic;

import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class AlumnoLogicImpl implements AlumnoLogic {
	
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
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoLogicImpl.class);
    
        
    Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);

   @Override
    public ResponseEntity<ApiError> createAlumno (Credenciales cred, List<alumnoDto> data) throws UserNotFoundException {
    	ApiError response = new ApiError();
    	String resp = "";
    	String identi= "";
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            AlumnoDao dao =  new AlumnoDao(conn);
            
           if(!data.isEmpty()) {
        	   for(alumnoDto dt: data) {
        		   
        		   resp = dao.createAlumno(dt);
        	   }
        	   if(!resp.isEmpty()) {
        		   response.setMessage("Success");
            	   response.setStatus(true);
            	   response.setInstant(Instant.now());
        	   }else {
        		   response.setMessage("Ocurrio un Error");
            	   response.setStatus(false);
            	   response.setInstant(Instant.now());
        	   }
        	  
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
    public ResponseEntity<ApiError> updateAlumno (Credenciales cred, List<alumnoDto> data) throws UserNotFoundException {
    	ApiError response = new ApiError();
        Connection conn = null;
        String resp= "";
        try {
            conn = Conexion.getConexion(cred);
            AlumnoDao dao =  new AlumnoDao(conn);

           
           for(alumnoDto dt: data) {
    		   
    		   resp = dao.updateAlumno(dt);
    	   }
           
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
   public ResponseEntity<ApiError> deleteAlumno (Credenciales cred, Integer data) throws UserNotFoundException {
   	ApiError response = new ApiError();
       Connection conn = null;
       String resp= "";
       try {
           conn = Conexion.getConexion(cred);
           AlumnoDao dao =  new AlumnoDao(conn);

   		   resp = dao.deleteAlumno(data);
          
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
    public pageResponse<alumnoDto> listarAlumno (Credenciales cred, Integer data) throws UserNotFoundException {
    	List<alumnoDto> response = new ArrayList<>();
    	pageResponse<alumnoDto> respons = new pageResponse<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            AlumnoDao dao =  new AlumnoDao(conn);

            response = dao.listarAlumno(data);
           if(!response.isEmpty()) {
        	   respons.setStatus(true);
        	   respons.setTotalItems(response.size());
        	   respons.setItems(response);
           }else {
        	   respons.setStatus(true);
        	   respons.setTotalItems(response.size());
        	   respons.setItems(response);
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
