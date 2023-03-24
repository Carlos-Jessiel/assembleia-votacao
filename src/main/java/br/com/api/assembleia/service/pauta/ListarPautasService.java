package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.dto.pauta.DadosListagemPautaDto;
import br.com.api.assembleia.repository.PautaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListarPautasService {

    private final PautaRepository repository;

    public ListarPautasService(PautaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<Page<DadosListagemPautaDto>> listar(Pageable paginacao) {
        return ResponseEntity.ok().body(repository.findAll(paginacao).map(DadosListagemPautaDto::new));

    }
}
