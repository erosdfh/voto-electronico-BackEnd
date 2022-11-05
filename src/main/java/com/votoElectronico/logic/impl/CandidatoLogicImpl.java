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
import com.votoElectronico.data.CandidatoGanadorDTO;
import com.votoElectronico.data.RegidorDto;
import com.votoElectronico.data.ReporteEleccionGeneralDTO;
import com.votoElectronico.data.ReporteEleccionGradoDTO;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.logic.CandidatoLogic;
import com.votoElectronico.logic.UsuarioLogic;

import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class CandidatoLogicImpl implements CandidatoLogic {
	
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
    
    private static final Logger log = LoggerFactory.getLogger(CandidatoLogicImpl.class);
    
        
    Credenciales cred = new Credenciales(url, username, password, timeout, read_only, maximunPoolSize, pool_name);

   @Override
    public ResponseEntity<ApiError> createCandidato (Credenciales cred, CandidatoDto data) throws UserNotFoundException {
    	ApiError response = new ApiError();
    	String resp = "";
    	String identi= "";
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);
            identi = dao.createCandidato(data);
            
            System.out.print("idCAndidato: "+identi);
            
           if(!identi.isEmpty()) {
        	   for(RegidorDto dt: data.getRegidor()) {
        		   
        		   resp = dao.createRegidor(dt, Integer.parseInt(identi));
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
   public ResponseEntity<ApiError> deleteCandidato (Credenciales cred, Integer data) throws UserNotFoundException {
   	ApiError response = new ApiError();
       Connection conn = null;
       String resp= "";
       try {
           conn = Conexion.getConexion(cred);
           CandidatoDao dao =  new CandidatoDao(conn);

   		   resp = dao.deleteCandidato(data);
          
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
    public ResponseEntity<ApiError> updateCandidato (Credenciales cred, CandidatoDto data) throws UserNotFoundException {
    	ApiError response = new ApiError();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);

           String resp = dao.updateCandidato(data);
           
           for(RegidorDto dt: data.getRegidor()) {
    		   
    		   resp = dao.updateRegidor(dt,data.getIdCandidato());
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
    public pageResponse<CandidatoDto> listarCandidato (Credenciales cred, Integer data) throws UserNotFoundException {
    	List<CandidatoDto> response = new ArrayList<>();
    	List<RegidorDto> resp = new ArrayList<>();
    	pageResponse<CandidatoDto> respons = new pageResponse<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);

            response = dao.listarCandidato(data);
           if(!response.isEmpty()) {
        	   for(CandidatoDto rs: response) {
        		   resp = dao.listarRegidor(rs.getIdCandidato());
        		   rs.setRegidor(resp); 
        	   }
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
    
    @Override
    public pageResponse<CandidatoGanadorDTO> listGanador (Credenciales cred) throws UserNotFoundException {
    	List<CandidatoGanadorDTO> response = new ArrayList<>();
    	pageResponse<CandidatoGanadorDTO> respons = new pageResponse<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);

            response = dao.listGanador();
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
    
    @Override
    public pageResponse<ReporteEleccionGradoDTO> lisEleccionPorGrado(Credenciales cred, Integer grado) throws UserNotFoundException{
    	List<ReporteEleccionGradoDTO> response = new ArrayList<>();
    	pageResponse<ReporteEleccionGradoDTO> respons = new pageResponse<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);

            response = dao.lisEleccionPorGrado(grado);
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

    @Override
    public pageResponse<ReporteEleccionGeneralDTO> listEleccionGeneral (Credenciales cred, ReporteEleccionGeneralDTO data) throws UserNotFoundException {
    	List<ReporteEleccionGeneralDTO> response = new ArrayList<>();
    	pageResponse<ReporteEleccionGeneralDTO> respons = new pageResponse<>();
        Connection conn = null;
        try {
            conn = Conexion.getConexion(cred);
            CandidatoDao dao =  new CandidatoDao(conn);

            response = dao.listEleccionGeneral(data);
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
    
}
