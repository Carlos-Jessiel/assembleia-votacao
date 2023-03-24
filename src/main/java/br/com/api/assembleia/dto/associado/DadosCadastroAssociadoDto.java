package br.com.api.assembleia.dto.associado;

import br.com.api.assembleia.model.Associado;

public record DadosCadastroAssociadoDto(

        String nome,

        String cpf

) {
    public static Associado criarAssociado(DadosCadastroAssociadoDto dados) {
        var model = new Associado();
        model.setCpf(dados.cpf);
        model.setNome(dados.nome);

        return model;

    }
}
