package br.com.api.assembleia.service.voto;

import br.com.api.assembleia.configuracoes.ConfigTempoDeSessao;
import br.com.api.assembleia.dto.voto.DadosVotacaoDto;
import br.com.api.assembleia.model.Voto;
import br.com.api.assembleia.model.VotoDados;
import br.com.api.assembleia.model.enums.EscolhaVoto;
import br.com.api.assembleia.model.enums.Status;
import br.com.api.assembleia.repository.AssociadoRepository;
import br.com.api.assembleia.repository.PautaRepository;
import br.com.api.assembleia.repository.VotoRepository;
import br.com.api.assembleia.validador.base.ErroDto;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import br.com.api.assembleia.validador.voto.VotoValidacoes;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    private final PautaRepository pautaRepository;

    private final AssociadoRepository associadoRepository;

    private final VotoRepository votoRepository;

    private final ConfigTempoDeSessao configTempoDeSessao;

    public VotoService(PautaRepository pautaRepository,
                       AssociadoRepository associadoRepository,
                       VotoRepository votoRepository,
                       ConfigTempoDeSessao configTempoDeSessao){
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
        this.votoRepository = votoRepository;
        this.configTempoDeSessao = configTempoDeSessao;
    }

    @Transactional
    public ResponseEntity votar(DadosVotacaoDto dados) {
        if (!pautaRepository.existsById(dados.id_pauta())){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id_pauta", "Não foi encontrada pauta para o id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }

        if(!associadoRepository.existsById(dados.id_associado())){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id_associado", "Não foi encontrado associado para o id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }

        List<ErroDto> erros_dados_votacao = VotoValidacoes.executar(dados);

        if (!erros_dados_votacao.isEmpty()){
            BaseDtoErro builder = new BaseDtoErro(HttpStatus.BAD_REQUEST);
            builder.addErros(erros_dados_votacao);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        var pauta = pautaRepository.getReferenceById(dados.id_pauta());
        var associado = associadoRepository.getReferenceById(dados.id_associado());

        VotoDados votoDados = new VotoDados(associado, pauta);
        Optional<Voto> optionalVoto = votoRepository.findById(votoDados);
        List<ErroDto> erros_dados_pauta = VotoValidacoes.executar(pauta, optionalVoto);

        if (!erros_dados_pauta.isEmpty()){
            BaseDtoErro builder = new BaseDtoErro(HttpStatus.BAD_REQUEST);
            builder.addErros(erros_dados_pauta);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        if (dados.voto().equals(EscolhaVoto.SIM)){
            pauta.setVotoSim(pauta.getVotoSim() + 1);
        }else {
            pauta.setVotoNao(pauta.getVotoNao() + 1);
        }

        Voto voto = new Voto(votoDados, dados.voto());

        pautaRepository.save(pauta);
        configTempoDeSessao.tempoDeSessao(pauta.getId(), pauta.getHorarioFim());
        votoRepository.save(voto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseDtoSucesso<>
                        ("Voto cadastrado com sucesso.", HttpStatus.CREATED).get());
    }

    public void encerrarVotacao(Long id) {
        var model = pautaRepository.findById(id).get();
        model.setStatus(Status.VOTACAO_FINALIZADA);
        pautaRepository.save(model);
    }
}
