package br.com.api.assembleia.dto.voto;

import br.com.api.assembleia.model.enums.EscolhaVoto;

public record DadosVotacaoDto(

        Long id_associado,
        Long id_pauta,
        EscolhaVoto voto
) {
}
