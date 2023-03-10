package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.repository.PautaRepository;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletarPautaService {

    private final PautaRepository repository;

    public DeletarPautaService(PautaRepository repository){
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity deletar(Long id) {

        if (!repository.existsById(id)){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.NOT_FOUND);
            erro.addErro("id", "Não foi encontrada pauta pelo id informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get());
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSucesso<>("Pauta deletada com sucesso.", HttpStatus.OK).get());
    }
}
