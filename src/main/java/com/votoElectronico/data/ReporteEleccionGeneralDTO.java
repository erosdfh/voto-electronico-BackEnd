package com.votoElectronico.data;


import lombok.Data;

@Data
public class ReporteEleccionGeneralDTO {
	private Integer idAlumno;
	private String dni;
	private String apellidosNombres;
	private String grado;
	private String seccion;
	private Integer voto; 
	private String nivel;

	private Integer idNivel;
}
