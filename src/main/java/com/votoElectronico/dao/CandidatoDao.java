package com.votoElectronico.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.CandidatoGanadorDTO;
import com.votoElectronico.data.RegidorDto;
import com.votoElectronico.data.ReporteEleccionGeneralDTO;
import com.votoElectronico.data.ReporteEleccionGradoDTO;
import com.votoElectronico.data.UsuarioDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDao {
    private Connection conn;


    private static final Logger log = LoggerFactory.getLogger(CandidatoDao.class);

    public CandidatoDao(Connection conn) {
        this.conn = conn;
    }

    public String createCandidato(CandidatoDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_create_candidato(?,?,?,?,?) }";
             CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String name =data.getCandidato() == null? "": data.getCandidato();
            String image =data.getImage() == null? "": data.getImage();
            String part =data.getPartidoPolitico()==null ? "": data.getPartidoPolitico();
            String log =data.getLogo() == null? "": data.getLogo();
            String obs=data.getObservacion() == null? "": data.getObservacion(); 
            
            cstm.setString(1, name);
            cstm.setString(2, image);
            cstm.setString(3, part);
            cstm.setString(4, log);
            cstm.setString(5, obs);
           
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	
            	resultado=rs.getString("idCandid");
            	
            }
             
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
    public String deleteCandidato(Integer data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);
            
            String sql = "{ call sp_delete_candidato(?) }";
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
    public String createRegidor(RegidorDto data, Integer id) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);
            System.out.println("DataAquii"+data);
            System.out.println("idaquiiii"+id);
            String sql = "{ call sp_registrar_regidores(?,?,?) }";
             CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String name =data.getRegidor() == null? "": data.getRegidor();
            String carg =data.getCargo() == null? "": data.getCargo();
            Integer iden =id==null ? 0: id;
            
            cstm.setString(1, name);
            cstm.setString(2, carg);
            cstm.setInt(3, iden);
           
            ResultSet rs = cstm.executeQuery();
            resultado=rs.toString();
           
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
    
    public String updateCandidato(CandidatoDto data) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);
            
            String sql = "{ call sp_update_candidato(?,?,?,?,?,?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String name =data.getCandidato() == null? "": data.getCandidato();
            String image =data.getImage() == null? "": data.getImage();
            String part =data.getPartidoPolitico()==null ? "": data.getPartidoPolitico();
            String log =data.getLogo() == null? "": data.getLogo();
            Integer id =data.getIdCandidato() == null? 0: data.getIdCandidato();
            String obs=data.getObservacion() == null? "": data.getObservacion(); 
            
            cstm.setString(1, name);
            cstm.setString(2, image);
            cstm.setString(3, part);
            cstm.setString(4, log);
            cstm.setInt(5, id);
            cstm.setString(6, obs);
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
    
    public String updateRegidor(RegidorDto data, Integer idCandidato) {
    	String resultado="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_update_regidores(?,?,?,?) }";
             CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String name =data.getRegidor() == null? "": data.getRegidor();
            String carg =data.getCargo() == null? "": data.getCargo();
            Integer iden =data.getIdRegidor()==null ? 0: data.getIdRegidor();
            Integer idCan = idCandidato==null ? 0: idCandidato;
            cstm.setString(1, name);
            cstm.setString(2, carg);
            cstm.setInt(3, iden);
            cstm.setInt(4, idCan);
            
            ResultSet rs = cstm.executeQuery();
            resultado=rs.toString();
           
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
    
    public List<CandidatoDto> listarCandidato(Integer data) {
    	List<CandidatoDto> response = new ArrayList<>();
    	System.out.print(data);
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_listar_candidato(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer id=data==null ? 0: data;
            
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	CandidatoDto dato= new CandidatoDto();
            	dato.setIdCandidato(rs.getInt("idCandidato"));
            	dato.setCandidato(rs.getString("candidato"));
            	dato.setImage(rs.getString("image"));
            	dato.setPartidoPolitico(rs.getString("partidoPolitico"));
            	dato.setLogo(rs.getString("logo"));
            	dato.setObservacion(rs.getString("observacion"));
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
    
    public List<RegidorDto> listarRegidor(Integer data) {
    	List<RegidorDto> response = new ArrayList<>();
    	System.out.print(data);
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payload = ow.writeValueAsString(data);

            String sql = "{ call sp_listar_regidores(?) }";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer id=data==null ? 0: data;
            
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
            	RegidorDto dato= new RegidorDto();
            	dato.setIdRegidor(rs.getInt("idRegidor"));
            	dato.setRegidor(rs.getString("regidor"));
            	dato.setCargo(rs.getString("cargo"));
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
    public List<CandidatoGanadorDTO> listGanador(){
    	List<CandidatoGanadorDTO> response = new ArrayList<>();
    	try {
            String sql = "{call sp_ganador}";
            CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs =cstm.executeQuery();
            while(rs.next()) {
            	CandidatoGanadorDTO resp = new CandidatoGanadorDTO();
            	resp.setIdCandidato(rs.getInt("idCandidato"));
            	resp.setCandidato(rs.getString("candidato"));
            	resp.setImage(rs.getString("image"));
            	resp.setPartidoPolitico(rs.getString("partidoPolitico"));
            	resp.setLogo(rs.getString("logo"));
            	resp.setCantidad(rs.getInt("cantidad"));
            	response.add(resp);
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
    public List<ReporteEleccionGradoDTO> lisEleccionPorGrado(Integer grado){
    	List<ReporteEleccionGradoDTO> response = new ArrayList<>();
    	try {
			String sql = "{call sp_reporte_conteo(?)}";
			CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			  Integer grad =grado == null? 0: grado;
	            
	            cstm.setInt(1, grad);
	          ResultSet rs = cstm.executeQuery();
			while(rs.next()) {
				ReporteEleccionGradoDTO resp = new ReporteEleccionGradoDTO();
				resp.setGrado(rs.getString("grado"));
				resp.setSeccion(rs.getNString("seccion"));
				resp.setCantidad(rs.getInt("total"));
				response.add(resp);
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
    
    public List<ReporteEleccionGeneralDTO> listEleccionGeneral(ReporteEleccionGeneralDTO data){
    	List<ReporteEleccionGeneralDTO> response = new ArrayList<>();
    	try {
			String sql = "{ call sp_reporte_por_grado(?,?,?,?)}";
			CallableStatement cstm = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String grad = data.getGrado() == null ? "": data.getGrado();
			String secc = data.getSeccion() == null ? "" : data.getSeccion();
			Integer idNiv = data.getIdNivel() ==null ? 0: data.getIdNivel();
			Integer vt =data.getVoto() == null ? 0 : data.getVoto();
            cstm.setString(1, grad);
            cstm.setString(2, secc);
            cstm.setInt(3, idNiv);
            cstm.setInt(4, vt);
			ResultSet rs = cstm.executeQuery();
			while(rs.next()) {
				ReporteEleccionGeneralDTO resp = new ReporteEleccionGeneralDTO();
				resp.setIdAlumno(rs.getInt("idAlumno"));
				resp.setDni(rs.getString("dni"));
				resp.setApellidosNombres(rs.getString("apellidos_nombres"));
				resp.setGrado(rs.getString("grado"));
				resp.setSeccion(rs.getString("seccion"));
				resp.setVoto(rs.getInt("voto"));
				resp.setNivel(rs.getString("nivel"));
				response.add(resp);
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