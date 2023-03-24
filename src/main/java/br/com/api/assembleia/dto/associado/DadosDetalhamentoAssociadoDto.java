package br.com.api.assembleia.dto.associado;


import br.com.api.assembleia.model.Associado;

public record DadosDetalhamentoAssociadoDto(
        Long id,
        String nome,
        String cpf
) {

    public DadosDetalhamentoAssociadoDto(Associado model) {
        this(model.getId(),
                model.getNome(),
                model.getCpf());
    }
}
