package br.com.api.assembleia.dto.pauta;

import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;

public record DadosCadastroPautaDto(

        String titulo,

        String descricao

) {
        public static Pauta criarPauta(DadosCadastroPautaDto dados) {
                Pauta model = new Pauta();
                model.setTitulo(dados.titulo);
                model.setDescricao(dados.descricao);
                model.setStatus(Status.PENDENTE_PARA_LIBERACAO);
                model.setVotoNao(0);
                model.setVotoSim(0);

                return model;

        }
}
