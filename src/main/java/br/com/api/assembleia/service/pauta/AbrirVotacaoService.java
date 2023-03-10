package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.configuracoes.ConfigTempoDeSessao;
import br.com.api.assembleia.dto.pauta.DadosDetalhamentoPautaDto;
import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;
import br.com.api.assembleia.repository.PautaRepository;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class AbrirVotacaoService {

    private final PautaRepository repository;

    private final ConfigTempoDeSessao configTempoDeSessao;

    public AbrirVotacaoService(PautaRepository repository,
                               ConfigTempoDeSessao configTempoDeSessao){
        this.repository = repository;
        this.configTempoDeSessao = configTempoDeSessao;
    }


    public ResponseEntity abrirVotacao(Long idPauta, Integer tempo) {
        Optional<Pauta> pauta = repository.findById(idPauta);
        if (pauta.isEmpty()){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.BAD_REQUEST);
            erro.addErro("id-pauta", "Não há pauta cadastrada para o id informado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.get());
        }

        tempo = tempo == null || tempo == 0 ? 1 : tempo;
        var pautaModel = pauta.get();

        pautaModel.setHorarioInicio(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        pautaModel.setHorarioFim(pautaModel.getHorarioInicio().plusMinutes(tempo));
        pautaModel.setStatus(Status.ABERTO_PARA_VOTACAO);

        repository.save(pautaModel);
        configTempoDeSessao.tempoPauta(pautaModel.getId());

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSucesso<>(new DadosDetalhamentoPautaDto(pautaModel), HttpStatus.OK).get());
    }
}
