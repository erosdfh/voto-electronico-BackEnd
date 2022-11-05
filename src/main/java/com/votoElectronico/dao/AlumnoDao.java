package com.votoElectronico.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.RegidorDto;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.alumnoDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao {
    private Connection conn;


    private static final Logger log = LoggerFactory.getLogger(AlumnoDao.class);

    public AlumnoDao(Connection conn) {
        this.conn = conn;
    }

    public String createAlumno(alumnoDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_insertar_alumno(?,?,?,?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String dni =data.getDni() == null? "": data.getDni();
            String ape_nom =data.getNombresApellidos() == null? "": data.getNombresApellidos();
            String grad =data.getGrado()==null ? "": data.getGrado();
            String secc =data.getSeccion() == null? "": data.getSeccion();
            Integer id_niv =data.getIdNivel() == null? 0: data.getIdNivel();
            
            cstm.setString(1, dni);
            cstm.setString(2, ape_nom);
            cstm.setString(3, grad);
            cstm.setString(4, secc);
            cstm.setInt(5, id_niv);
           
            ResultSet rs = cstm.executeQuery();
            resultado= rs.toString();
             
            System.out.print("devuelve valor: "+resultado);
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
  
    
    public String updateAlumno(alumnoDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);
            
            String sql = "{ call sp_update_alumno(?,?,?,?,?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String dni =data.getDni() == null? "": data.getDni();
            String ape_nom =data.getNombresApellidos() == null? "": data.getNombresApellidos();
            String grad =data.getGrado()==null ? "": data.getGrado();
            String secc =data.getSeccion() == null? "": data.getSeccion();
            Integer id_niv =data.getIdNivel() == null? 0: data.getIdNivel();
            Integer id=data.getIdalumno() == null? 0: data.getIdalumno(); 
            
            cstm.setString(1, dni);
            cstm.setString(2, ape_nom);
            cstm.setString(3, grad);
            cstm.setString(4, secc);
            cstm.setInt(5, id_niv);
            cstm.setInt(6, id);
            ResultSet rs = cstm.executeQuery();
            resultado = rs.toString();
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
    public String deleteAlumno(Integer data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);
            
            String sql = "{ call sp_delete_alumno(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer id=data == null? 0: data; 
            
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            resultado = rs.toString();
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
    public List<alumnoDto> listarAlumno(Integer data) {
    	List<alumnoDto> response = new ArrayList<>();
    	System.out.print(data);
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_listar_alumno(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer id=data==null ? 0: data;
            
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	alumnoDto dato= new alumnoDto();
            	dato.setIdalumno(rs.getInt("idalumno"));
            	dato.setDni(rs.getString("dni"));
            	dato.setNombresApellidos(rs.getString("apellidos_nombres"));
            	dato.setGrado(rs.getString("grado"));
            	dato.setSeccion(rs.getString("seccion"));
            	dato.setNivel(rs.getString("nivel"));
            	dato.setIdNivel(rs.getInt("id_nivel"));
            	response.add(dato);
            	
            }
            
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return response;

    }
    
   
}