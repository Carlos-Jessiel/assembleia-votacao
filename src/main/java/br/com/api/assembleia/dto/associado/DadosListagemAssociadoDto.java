package br.com.api.assembleia.dto.associado;

import br.com.api.assembleia.model.Associado;

public record DadosListagemAssociadoDto(

        Long id,
        String nome,
        String cpf

) {

    public DadosListagemAssociadoDto(Associado associado){
        this(associado.getId(),
                associado.getNome(),
                associado.getCpf());
    }
}
