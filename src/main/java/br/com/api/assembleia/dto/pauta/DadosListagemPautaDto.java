package br.com.api.assembleia.dto.pauta;

import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;

public record DadosListagemPautaDto(

        Long id,
        String titulo,
        String descricao,
        Integer votoSim,
        Integer votoNao,
        Status status
) {

    public DadosListagemPautaDto(Pauta pauta){
        this(pauta.getId(),
                pauta.getTitulo(),
                pauta.getDescricao(),
                pauta.getVotoSim(),
                pauta.getVotoNao(),
                pauta.getStatus());
    }
}
