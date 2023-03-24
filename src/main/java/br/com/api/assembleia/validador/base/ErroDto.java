package br.com.api.assembleia.validador.base;

import lombok.Data;

@Data
public class ErroDto {

    private String campo;
    private String mensagem;


    public ErroDto(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }


}


