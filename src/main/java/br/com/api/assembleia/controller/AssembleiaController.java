package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.service.CadastrarPautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

    private final CadastrarPautaService cadastrarPautaService;

    public AssembleiaController(CadastrarPautaService cadastrarPautaService){
        this.cadastrarPautaService = cadastrarPautaService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroDto dados){
        return cadastrarPautaService.cadastrar(dados);
    }
}
