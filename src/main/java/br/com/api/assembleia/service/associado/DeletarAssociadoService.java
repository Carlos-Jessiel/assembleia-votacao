package br.com.api.assembleia.service.associado;

import br.com.api.assembleia.repository.AssociadoRepository;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletarAssociadoService {

    private final AssociadoRepository repository;

    public DeletarAssociadoService(AssociadoRepository repository){
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity deletar(Long id) {
        if (!repository.existsById(id)){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id", "Não foi encontrado associado para o id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }

        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseDtoSucesso<>
                        ("Associado deletado com sucesso.", HttpStatus.OK).get());
    }
}
