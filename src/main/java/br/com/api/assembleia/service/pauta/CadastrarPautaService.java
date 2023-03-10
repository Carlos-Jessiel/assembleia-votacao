package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.dto.pauta.DadosDetalhamentoDto;
import br.com.api.assembleia.repository.PautaRepository;
import br.com.api.assembleia.validador.base.ErroDto;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import br.com.api.assembleia.validador.pauta.PautaValidacoes;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarPautaService {

    private final PautaRepository repository;

    public CadastrarPautaService(PautaRepository repository){
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity cadastrar(DadosCadastroDto dados) {
        List<ErroDto> erros = PautaValidacoes.executar(dados);

        if (!erros.isEmpty()){
            BaseDtoErro builder = new BaseDtoErro(HttpStatus.BAD_REQUEST);
            builder.addErros(erros);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        var model = DadosCadastroDto.criarPauta(dados);
        repository.save(model);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseDtoSucesso<>
                        (new DadosDetalhamentoDto(model), HttpStatus.CREATED).get());
    }
}
