package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.dto.pauta.DadosListagemPautaDto;
import br.com.api.assembleia.repository.PautaRepository;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuscarPautaPorIdService {

    private final PautaRepository repository;

    public BuscarPautaPorIdService(PautaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity buscarPorId(Long id) {
        if (!repository.existsById(id)){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id", "Não foi encontrada pauta para o id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }
        var model = repository.getReferenceById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseDtoSucesso<>
                        (new DadosListagemPautaDto(model), HttpStatus.OK).get());
    }
}
