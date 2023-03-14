package br.com.api.assembleia.service.associado;

import br.com.api.assembleia.dto.associado.DadosListagemAssociadoDto;
import br.com.api.assembleia.repository.AssociadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListarAssociadoService {

    private final AssociadoRepository repository;

    public ListarAssociadoService(AssociadoRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<Page<DadosListagemAssociadoDto>> listar(Pageable paginacao) {
        return ResponseEntity.ok().body(repository.findAll(paginacao).map(DadosListagemAssociadoDto::new));
    }
}
