package br.com.api.assembleia.validador.builder;

import br.com.api.assembleia.validador.base.BaseDto;
import br.com.api.assembleia.validador.base.ErroDto;
import br.com.api.assembleia.validador.base.StatusDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class BaseDtoErro {

    private BaseDto<Void> resultado;

    public BaseDtoErro(HttpStatus status){
        StatusDto statusDto = new StatusDto(status.value(), status.name());
        resultado = new BaseDto<>(null, new ArrayList<>(), statusDto);
    }

    public void addErro (String dado, String mensagem){
        ErroDto erroDto = new ErroDto(dado, mensagem);
        resultado.adicionaErro(erroDto);
    }

    public void addErros(List<ErroDto> erros){
        resultado.setErros(erros);
    }

    public BaseDto get() {
        return resultado;
    }
}
