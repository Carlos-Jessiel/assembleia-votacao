package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.service.associado.CadastrarAssociadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final CadastrarAssociadoService cadastrarAssociadoService;

    public AssociadoController(CadastrarAssociadoService cadastrarAssociadoService){
        this.cadastrarAssociadoService = cadastrarAssociadoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody DadosCadastroAssociadoDto dados){
        return cadastrarAssociadoService.cadastrar(dados);
    }
}
