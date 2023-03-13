package br.com.api.assembleia.validador.associado;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.validador.CpfValidator;
import br.com.api.assembleia.validador.base.ErroDto;

import java.util.ArrayList;
import java.util.List;

public class AssociadoValidacoes {

    public static List<ErroDto> validarCadastro(DadosCadastroAssociadoDto dados) {
        List<ErroDto> erros = new ArrayList<>();

        if(dados.nome() == null || dados.nome().isBlank()){
            erros.add(new ErroDto("nome", "Campo nome não pode ser vazio."));
        }

        if(dados.cpf() == null || dados.cpf().isBlank()){
            erros.add(new ErroDto("cpf", "Campo cpf não pode ser vazio."));
        } else if(!CpfValidator.validateCpf(dados.cpf())){
            erros.add(new ErroDto("cpf", "CPF inválido."));
        }

        return erros;
    }
}
