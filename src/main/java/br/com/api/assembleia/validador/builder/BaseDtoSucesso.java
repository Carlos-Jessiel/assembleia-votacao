package br.com.api.assembleia.validador.builder;

import br.com.api.assembleia.validador.base.BaseDto;
import br.com.api.assembleia.validador.base.StatusDto;
import org.springframework.http.HttpStatus;

public class BaseDtoSucesso<E> {

    private final BaseDto<E> resultado;

    public BaseDtoSucesso(E resultado, HttpStatus status){
        StatusDto statusDto = new StatusDto(status.value(), status.name());
        this.resultado = new BaseDto<>(resultado, null, statusDto);
    }

    public BaseDto get() {
        return resultado;
    }
}
