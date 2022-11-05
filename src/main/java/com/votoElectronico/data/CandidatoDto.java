package com.votoElectronico.data;


import java.util.List;

import lombok.Data;

@Data
public class CandidatoDto {
	private Integer idCandidato;
    private String candidato;
    private String image;
    private String partidoPolitico;
    private String logo;
    private String Observacion;
    private List<RegidorDto> regidor;
   
}
