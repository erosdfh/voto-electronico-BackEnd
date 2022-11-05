package com.votoElectronico.logic;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.beans.response.UserNotFoundException;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.alumnoDto;
import com.votoElectronico.data.pageResponse;


public interface AlumnoLogic {
	public ResponseEntity<ApiError> createAlumno (Credenciales cred, List<alumnoDto> data) throws UserNotFoundException ;
    public ResponseEntity<ApiError> updateAlumno (Credenciales cred, List<alumnoDto> data) throws UserNotFoundException ;
    public ResponseEntity<ApiError> deleteAlumno (Credenciales cred, Integer data) throws UserNotFoundException ;
    public pageResponse<alumnoDto> listarAlumno (Credenciales cred, Integer data) throws UserNotFoundException ;
}
