package br.com.api.assembleia.service;

import br.com.api.assembleia.dto.DadosCadastroDto;
import br.com.api.assembleia.repository.PautaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPautaService {

    private final PautaRepository repository;

    public CadastrarPautaService(PautaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity cadastrar(DadosCadastroDto dados) {
        //TODO: desenvolver
        return null;
    }
}
