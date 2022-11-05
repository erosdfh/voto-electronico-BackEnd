package com.votoElectronico.logic;


import org.springframework.http.ResponseEntity;

import com.votoElectronico.beans.Credenciales;
import com.votoElectronico.beans.response.UserNotFoundException;
import com.votoElectronico.data.ApiError;
import com.votoElectronico.data.CandidatoDto;
import com.votoElectronico.data.CandidatoGanadorDTO;
import com.votoElectronico.data.ReporteEleccionGeneralDTO;
import com.votoElectronico.data.ReporteEleccionGradoDTO;
import com.votoElectronico.data.pageResponse;


public interface CandidatoLogic {
	public ResponseEntity<ApiError> createCandidato (Credenciales cred, CandidatoDto data) throws UserNotFoundException ;
    public ResponseEntity<ApiError> updateCandidato (Credenciales cred, CandidatoDto data) throws UserNotFoundException ;
    public ResponseEntity<ApiError> deleteCandidato (Credenciales cred, Integer data) throws UserNotFoundException ;
    public pageResponse<CandidatoDto> listarCandidato (Credenciales cred, Integer data) throws UserNotFoundException ;
    public pageResponse<CandidatoGanadorDTO> listGanador(Credenciales cred) throws UserNotFoundException;
	public pageResponse<ReporteEleccionGradoDTO> lisEleccionPorGrado(Credenciales cred, Integer grado) throws UserNotFoundException;
	public pageResponse<ReporteEleccionGeneralDTO> listEleccionGeneral(Credenciales cred, ReporteEleccionGeneralDTO data) throws UserNotFoundException;
	

}
