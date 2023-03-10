package br.com.api.assembleia.validador.pauta;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.validador.base.ErroDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PautaValidacoes {

    public static List<ErroDto> executar(DadosCadastroDto dados){
        List<ErroDto> erros = new ArrayList<>();

        if(dados.titulo() == null || dados.titulo().isBlank()){
            erros.add(new ErroDto("titulo","Campo obrigatório."));
        }
        if(dados.descricao().isBlank()){
            erros.add(new ErroDto("descricao", "Campo obrigatório."));
        }
        return erros;
    }


}
