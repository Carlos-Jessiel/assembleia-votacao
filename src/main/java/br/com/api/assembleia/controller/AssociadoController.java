package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.dto.associado.DadosListagemAssociadoDto;
import br.com.api.assembleia.service.associado.CadastrarAssociadoService;
import br.com.api.assembleia.service.associado.ListarAssociadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final CadastrarAssociadoService cadastrarAssociadoService;

    private final ListarAssociadoService listarAssociadoService;

    public AssociadoController(CadastrarAssociadoService cadastrarAssociadoService,
                               ListarAssociadoService listarAssociadoService){
        this.cadastrarAssociadoService = cadastrarAssociadoService;
        this.listarAssociadoService = listarAssociadoService;
    }

    @PostMapping
    public ResponseEntity cadastrar (@RequestBody DadosCadastroAssociadoDto dados){
        return cadastrarAssociadoService.cadastrar(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAssociadoDto>> listar (@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return listarAssociadoService.listar(paginacao);
    }
}
