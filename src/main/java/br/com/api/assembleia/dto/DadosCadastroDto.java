package br.com.api.assembleia.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroDto(

        @NotNull
        String titulo,

        @NotNull
        String descricao

) {
}
