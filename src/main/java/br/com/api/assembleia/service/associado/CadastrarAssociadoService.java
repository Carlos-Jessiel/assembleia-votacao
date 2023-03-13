package br.com.api.assembleia.service.associado;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.repository.AssociadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastrarAssociadoService {

    private final AssociadoRepository repository;

    public CadastrarAssociadoService(AssociadoRepository repository){
        this.repository = repository;
    }

    public ResponseEntity cadastrar(DadosCadastroAssociadoDto dados) {
        //TODO: desenvolver
        return null;
    }
}
