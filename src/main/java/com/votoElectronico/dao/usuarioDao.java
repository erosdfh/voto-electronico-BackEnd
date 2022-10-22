package com.votoElectronico.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.votoElectronico.data.UsuarioDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class usuarioDao {
    private Connection conn;


    private static final Logger log = LoggerFactory.getLogger(usuarioDao.class);

    public usuarioDao(Connection conn) {
        this.conn = conn;
    }

    public String createPatients(UsuarioDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call create_users(?,?,?,?,?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String lastname =data.getFirst_surname() == null? "": data.getFirst_surname();
            String second_name =data.getSecond_surname() == null? "": data.getSecond_surname();
            String name =data.getName()==null ? "": data.getName();
            String correo =data.getEmail() == null? "": data.getEmail();
            String user=data.getName_user() ==null ?"": data.getName_user();
            String pass=data.getPsw()==null ? "": data.getPsw();
            
            cstm.setString(1, lastname);
            cstm.setString(2, second_name);
            cstm.setString(3, name);
            cstm.setString(4, correo);
            cstm.setString(5, user);
            cstm.setString(6, pass);
            

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
    
    public String updatePatients(UsuarioDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call update_users(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String lastname =data.getFirst_surname() == null? "": data.getFirst_surname();
            String second_name =data.getSecond_surname() == null? "": data.getSecond_surname();
            String name =data.getName()==null ? "": data.getName();
            String correo =data.getEmail() == null? "": data.getEmail();
            String user=data.getName_user() ==null ?"": data.getName_user();
            String pass=data.getPsw()==null ? "": data.getPsw();
            String estado=data.getEstado()==null ? "": data.getEstado();
            Integer id=data.getIdUsers()==null ? 0: data.getIdUsers();
            
            cstm.setString(1, lastname);
            cstm.setString(2, second_name);
            cstm.setString(3, name);
            cstm.setString(4, correo);
            cstm.setString(5, user);
            cstm.setString(6, pass);
            cstm.setString(7, estado);
            cstm.setInt(8, id);
            

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
    
 
    
    public List<UsuarioDto> listarUsuario(Integer data) {
    	List<UsuarioDto> response = new ArrayList<>();
    	System.out.print(data);
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call list_users(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer id=data==null ? 0: data;
            
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	UsuarioDto dato= new UsuarioDto();
            	dato.setIdUsers(rs.getInt("idUsers"));
            	dato.setFirst_surname(rs.getString("first_surname"));
            	dato.setSecond_surname(rs.getString("second_surname"));
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