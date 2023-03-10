package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.dto.pauta.DadosListagemDto;
import br.com.api.assembleia.service.pauta.CadastrarPautaService;
import br.com.api.assembleia.service.pauta.ListarPautasService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

    private final CadastrarPautaService cadastrarPautaService;

    private final ListarPautasService listarPautasService;

    public AssembleiaController(CadastrarPautaService cadastrarPautaService,
                                ListarPautasService listarPautasService){
        this.cadastrarPautaService = cadastrarPautaService;
        this.listarPautasService = listarPautasService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroDto dados){
        return cadastrarPautaService.cadastrar(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return listarPautasService.listar(paginacao);
    }
}
