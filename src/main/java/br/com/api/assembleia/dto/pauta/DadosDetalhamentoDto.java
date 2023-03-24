package br.com.api.assembleia.dto.pauta;

import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;

public record DadosDetalhamentoDto(

        Long id,
        String titulo,
        String descricao,
        Status status
) {
    public DadosDetalhamentoDto(Pauta model) {
        this(model.getId(),
                model.getTitulo(),
                model.getDescricao(),
                model.getStatus());
    }
}
