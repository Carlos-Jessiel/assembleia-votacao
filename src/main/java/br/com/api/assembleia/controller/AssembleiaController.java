package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.pauta.DadosCadastroDto;
import br.com.api.assembleia.dto.pauta.DadosListagemDto;
import br.com.api.assembleia.service.pauta.*;
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

    private final AbrirVotacaoService abrirVotacaoService;

    private final ResultadoVotacaoService resultadoVotacaoService;

    public AssembleiaController(CadastrarPautaService cadastrarPautaService,
                                ListarPautasService listarPautasService,
                                BuscarPautaPorIdService buscarPautaPorIdService,
                                DeletarPautaService deletarPautaService,
                                AbrirVotacaoService abrirVotacaoService,
                                ResultadoVotacaoService resultadoVotacaoService){
        this.cadastrarPautaService = cadastrarPautaService;
        this.listarPautasService = listarPautasService;
        this.buscarPautaPorIdService = buscarPautaPorIdService;
        this.deletarPautaService = deletarPautaService;
        this.abrirVotacaoService = abrirVotacaoService;
        this.resultadoVotacaoService = resultadoVotacaoService;
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

    @PutMapping("/{idPauta}")
    public ResponseEntity abrirVotacao (@PathVariable Long idPauta, @RequestParam(defaultValue = "1") Integer tempo){
        return abrirVotacaoService.abrirVotacao(idPauta, tempo);
    }

    @GetMapping("/resultado/{idPauta}")
    public ResponseEntity resultadoVotacao(@PathVariable Long idPauta){
        return resultadoVotacaoService.resultadoVotacao(idPauta);
    }

}
