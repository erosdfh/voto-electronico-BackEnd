package com.votoElectronico.logic;


import org.springframework.http.ResponseEntity;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.beans.response.UserNotFoundException;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.UsuarioDto;
import com.votoElectronico.data.pageResponse;


public interface UsuarioLogic {
	public ResponseEntity<ApiError> createPatients (Credenciales cred, UsuarioDto data) throws UserNotFoundException ;
    public ResponseEntity<ApiError> updatePatients (Credenciales cred, UsuarioDto data) throws UserNotFoundException ;
    public pageResponse<UsuarioDto> listarUsuario (Credenciales cred, Integer data) throws UserNotFoundException ;
}
