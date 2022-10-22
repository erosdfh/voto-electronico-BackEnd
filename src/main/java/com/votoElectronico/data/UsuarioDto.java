package com.votoElectronico.data;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioDto {
	private Integer idUsers;
    private String first_surname;
    private String second_surname;
    private String name;
    private String email;
    private String name_user;
    private String psw;
    private Date fecha_creacion;
    private Date fecha_modificacion;
    private String estado;
}
