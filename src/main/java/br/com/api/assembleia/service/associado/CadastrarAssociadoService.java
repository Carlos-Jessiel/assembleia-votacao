package br.com.api.assembleia.service.associado;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.dto.associado.DadosDetalhamentoAssociadoDto;
import br.com.api.assembleia.repository.AssociadoRepository;
import br.com.api.assembleia.validador.associado.AssociadoValidacoes;
import br.com.api.assembleia.validador.base.ErroDto;
import br.com.api.assembleia.validador.builder.BaseDtoErro;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarAssociadoService {

    private final AssociadoRepository repository;

    public CadastrarAssociadoService(AssociadoRepository repository){
        this.repository = repository;
    }

    public ResponseEntity cadastrar(DadosCadastroAssociadoDto dados) {
        if (repository.existsByCpf(dados.cpf())){
            BaseDtoErro erro = new BaseDtoErro(HttpStatus.CONFLICT);
            erro.addErro("cpf", "CPF informado ja cadastrado.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro.get());
        }
        List<ErroDto> erros = AssociadoValidacoes.validarCadastro(dados);

        if (!erros.isEmpty()){
            BaseDtoErro builder = new BaseDtoErro(HttpStatus.BAD_REQUEST);
            builder.addErros(erros);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        var model = DadosCadastroAssociadoDto.criarAssociado(dados);
        repository.save(model);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseDtoSucesso<>
                        (new DadosDetalhamentoAssociadoDto(model), HttpStatus.CREATED).get());
    }
}
