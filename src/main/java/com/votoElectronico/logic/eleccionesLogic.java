package com.votoElectronico.logic;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.beans.response.UserNotFoundException;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.Login;
import com.votoElectronico.data.alumnoDto;
import com.votoElectronico.data.pageResponse;
import com.votoElectronico.data.resultLoginAlumnoDto;


public interface eleccionesLogic {
	public ResponseEntity<ApiError> elecciones (Credenciales cred, int idCandidato, int idAlumno) throws UserNotFoundException ;
	public pageResponse<resultLoginAlumnoDto> login (Credenciales cred, Login data) throws UserNotFoundException ;
	public pageResponse<Integer> conteoAlumnos (Credenciales cred, Integer voto) throws UserNotFoundException ;
	
}
