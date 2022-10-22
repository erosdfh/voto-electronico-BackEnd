package com.votoElectronico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.votoElectronico.beans.Credenciales;



public class Conexion {
	private static final Logger log = LoggerFactory.getLogger(Conexion.class);
	
	public static Connection getConexion(Credenciales cred) throws Exception{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DriverManager.setLoginTimeout(cred.getTimeout() / 1000);
			conn = DriverManager.getConnection(cred.getUrl(),cred.getUsername(),cred.getPassword());
			conn.setAutoCommit(true);
			conn.setNetworkTimeout(Executors.newSingleThreadExecutor(), cred.getTimeout());
		}catch (ClassNotFoundException e) {
			log.error("No se encontró el driver de MySQL: " + e.getMessage());
			e.printStackTrace();
			//throw e;
		}catch(SQLException e) {
			log.error("SQL Error en la conexión: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			log.error("Error en la conexión: " + e.getMessage());
			e.printStackTrace();
			//throw e;
		}
		return conn;
	}

}
