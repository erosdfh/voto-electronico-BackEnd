package com.votoElectronico.data;


import lombok.Data;

@Data
public class alumnoDto {
	private Integer idalumno;
	private String dni;
	private String nombresApellidos;
	private String grado;
	private String seccion;
	private Integer idNivel;
	private String nivel;
   
}
