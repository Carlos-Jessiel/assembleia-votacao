package br.com.api.assembleia.validador.base;

import lombok.Data;

@Data
public class StatusDto {

    private Integer codigo;
    private String nome;

    public StatusDto (Integer codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

}
