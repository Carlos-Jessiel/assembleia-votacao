package br.com.api.assembleia.validador.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseDto<E> implements Serializable {

    private E resultado;

    private List<ErroDto> erros;

    private StatusDto statusDto;


    public BaseDto (E resultado, List<ErroDto> erros, StatusDto statusDto){
        this.resultado = resultado;
        this.erros = erros;
        this.statusDto = statusDto;
    }

    public void adicionaErro(ErroDto erro){
        erros.add(erro);
    }

}
