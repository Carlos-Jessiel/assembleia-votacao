package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.dto.pauta.DadosListagemDto;
import br.com.api.assembleia.service.pauta.BuscarPautaPorIdService;
import br.com.api.assembleia.service.pauta.CadastrarPautaService;
import br.com.api.assembleia.service.pauta.DeletarPautaService;
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

    private final BuscarPautaPorIdService buscarPautaPorIdService;

    private final DeletarPautaService deletarPautaService;

    public AssembleiaController(CadastrarPautaService cadastrarPautaService,
                                ListarPautasService listarPautasService,
                                BuscarPautaPorIdService buscarPautaPorIdService,
                                DeletarPautaService deletarPautaService){
        this.cadastrarPautaService = cadastrarPautaService;
        this.listarPautasService = listarPautasService;
        this.buscarPautaPorIdService = buscarPautaPorIdService;
        this.deletarPautaService = deletarPautaService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroDto dados){
        return cadastrarPautaService.cadastrar(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return listarPautasService.listar(paginacao);
    }

    @GetMapping("{id}")
    public ResponseEntity buscarPorId (@PathVariable Long id){
        return buscarPautaPorIdService.buscarPorId(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return deletarPautaService.deletar(id);
    }

}
