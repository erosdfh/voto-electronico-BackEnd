package com.votoElectronico.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.votoElectronico.data.Login;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.resultLoginAlumnoDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EleccionesDao {
    private Connection conn;


    private static final Logger log = LoggerFactory.getLogger(EleccionesDao.class);

    public EleccionesDao(Connection conn) {
        this.conn = conn;
    }

    public String votocandidato(Integer idAl) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(idAl);
            String sql = "{ call sp_voto_candidato(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer lastname =idAl == null? 0: idAl;
            
            cstm.setInt(1, lastname);
            

            ResultSet rs = cstm.executeQuery();
            resultado = rs.toString();
            
            System.out.print("devuelve valor"+resultado);
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
    public String votoAlumno(Integer idAl) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(idAl);

            String sql = "{ call sp_elecciones(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer idAlumno =idAl == null? 0: idAl;
            
            cstm.setInt(1, idAlumno);
            

            ResultSet rs = cstm.executeQuery();
            resultado = rs.toString();
            
            System.out.print("devuelve valor"+resultado);
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
    public List<resultLoginAlumnoDto> login(Login data) {
    	List<resultLoginAlumnoDto> resultado = new ArrayList<>();
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_login(?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String usu =data.getUser() == null? "": data.getUser();
            String psw =data.getPass() == null? "": data.getPass();
           
            cstm.setString(1, usu);
            cstm.setString(2, psw);
            ResultSet rs = cstm.executeQuery();
            System.out.print("devuelve valor"+cstm);
            while (rs.next()) {
            	resultLoginAlumnoDto dato = new resultLoginAlumnoDto();
            	dato.setIdusuario(rs.getInt("respuesta"));
            	dato.setIdalumno(rs.getInt("idalumno"));
            	resultado.add(dato);
            }
            System.out.print("devuelve valor"+resultado);
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    public List<Integer> conteoAlumnos(Integer data) {
    	List<Integer> resultado = new ArrayList<>();
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_conteo_alumnos(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer usu =data;
           
            cstm.setInt(1, usu);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	resultLoginAlumnoDto dato = new resultLoginAlumnoDto();
            	resultado.add(rs.getInt("total"));
            }
            rs.close();
            cstm.close();
        } catch (SQLException ex) {
            log.info("Exception SQL: " + ex.getMessage());
        } catch (Exception ex) {
            log.info("Exception: " + ex.getMessage());
        }

        return resultado;

    }
    
    
   
}