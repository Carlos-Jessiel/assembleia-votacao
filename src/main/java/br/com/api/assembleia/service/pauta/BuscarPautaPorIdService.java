package br.com.api.assembleia.service.pauta;

import br.com.api.assembleia.dto.pauta.DadosDetalhamentoPautaDto;
import br.com.api.assembleia.repository.PautaRepository;
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
        var model = repository.getReferenceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSucesso<>(new DadosDetalhamentoPautaDto(model), HttpStatus.OK).get());
    }
}
