package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.pauta.DadosCadastroPautaDto;
import br.com.api.assembleia.dto.pauta.DadosDetalhamentoDto;
import br.com.api.assembleia.dto.pauta.DadosDetalhamentoPautaDto;
import br.com.api.assembleia.dto.pauta.DadosListagemPautaDto;
import br.com.api.assembleia.service.pauta.*;
import br.com.api.assembleia.validador.builder.BaseDtoSucesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assembleia")
@Tag(name = "Assembleia", description = "Cadastro, listagem, delete, busca, resultado e abertura de pauta para votação.")
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
    @Operation(summary = "Cadastrar pauta", description = "Cadastro de pauta passando titulo e descrição")
    @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosDetalhamentoDto.class))})
    public ResponseEntity cadastrar(@RequestBody DadosCadastroPautaDto dados){
        return cadastrarPautaService.cadastrar(dados);
    }

    @GetMapping
    @Operation(summary = "Listar pautas", description = "Lista paginada de pautas contendo as 10 primeiras pautas listadas por ID")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosListagemPautaDto.class))})
    public ResponseEntity<Page<DadosListagemPautaDto>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return listarPautasService.listar(paginacao);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar uma pauta", description = "Busca por uma pauta de ID específico")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosListagemPautaDto.class))})
    public ResponseEntity buscarPorId (@PathVariable Long id){
        return buscarPautaPorIdService.buscarPorId(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar pauta", description = "Deletar pauta por ID")
    public ResponseEntity deletar(@PathVariable Long id){
        return deletarPautaService.deletar(id);
    }

    @PutMapping("/{idPauta}")
    @Operation(summary = "Abrir votação", description = "Abre pauta informada pelo ID para receber votos com o tempo default de 1 minuto")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosDetalhamentoPautaDto.class))})
    public ResponseEntity abrirVotacao (@PathVariable Long idPauta, @RequestParam(defaultValue = "1") Integer tempo){
        return abrirVotacaoService.abrirVotacao(idPauta, tempo);
    }

    @GetMapping("/resultado/{idPauta}")
    @Operation(summary = "Resultado votação", description = "Informa o resultado da votação para uma pauta específica passando o ID da pauta")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosDetalhamentoPautaDto.class))})
    public ResponseEntity resultadoVotacao(@PathVariable Long idPauta){
        return resultadoVotacaoService.resultadoVotacao(idPauta);
    }

}
