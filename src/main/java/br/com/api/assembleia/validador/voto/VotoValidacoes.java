package br.com.api.assembleia.validador.voto;

import br.com.api.assembleia.dto.voto.DadosVotacaoDto;
import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.Voto;
import br.com.api.assembleia.model.enums.Status;
import br.com.api.assembleia.validador.base.ErroDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VotoValidacoes {

    public static List<ErroDto> executar(DadosVotacaoDto dados) {
        List<ErroDto> erros = new ArrayList<>();

        if (dados.id_pauta() == null){
            erros.add(new ErroDto("id_pauta", "Id da pauta não pode ser vazio."));
        }
        if (dados.id_associado() == null){
            erros.add(new ErroDto("id_associado", "Id do associado não pode ser vazio."));
        }

        return erros;
    }

    public static List<ErroDto> executar(Pauta pauta, Optional<Voto> optionalVoto) {
        List<ErroDto> erros = new ArrayList<>();

        if(!pauta.getStatus().equals(Status.ABERTO_PARA_VOTACAO)){
            erros.add(new ErroDto("status-votacao", "Pauta selecionada não esta aberta para receber votos."));
        }
        if(optionalVoto.isPresent()){
            erros.add(new ErroDto("voto", "Associado já consta voto para a pauta selecionada."));
        }

        return erros;
    }
}
