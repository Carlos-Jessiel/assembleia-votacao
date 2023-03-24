package br.com.api.assembleia.service.associado;

import br.com.api.assembleia.dto.associado.DadosDetalhamentoAssociadoDto;
import br.com.api.assembleia.repository.AssociadoRepository;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuscarAssociadoPorIdService {

    private final AssociadoRepository repository;

    public BuscarAssociadoPorIdService(AssociadoRepository repository){
        this.repository = repository;
    }

    public ResponseEntity buscarPorId(Long id) {
        if (!repository.existsById(id)){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id", "Não foi encontrado associado para o id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }
        var model = repository.getReferenceById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseDtoSucesso<>
                        (new DadosDetalhamentoAssociadoDto(model), HttpStatus.OK).get());
    }
}
