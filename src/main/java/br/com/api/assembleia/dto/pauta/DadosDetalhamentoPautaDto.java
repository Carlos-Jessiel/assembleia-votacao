package br.com.api.assembleia.dto.pauta;

import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;

import java.time.LocalDateTime;

public record DadosDetalhamentoPautaDto(

        Long id,
        String titulo,
        String descricao,
        Integer votoSim,
        Integer votoNao,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFim,
        Status status

) {
    public DadosDetalhamentoPautaDto(Pauta model) {
        this(model.getId(),
                model.getTitulo(),
                model.getDescricao(),
                model.getVotoSim(),
                model.getVotoNao(),
                model.getHorarioInicio(),
                model.getHorarioFim(),
                model.getStatus());
    }
}
