package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.associado.DadosCadastroAssociadoDto;
import br.com.api.assembleia.dto.associado.DadosDetalhamentoAssociadoDto;
import br.com.api.assembleia.dto.associado.DadosListagemAssociadoDto;
import br.com.api.assembleia.dto.pauta.DadosDetalhamentoPautaDto;
import br.com.api.assembleia.service.associado.BuscarAssociadoPorIdService;
import br.com.api.assembleia.service.associado.CadastrarAssociadoService;
import br.com.api.assembleia.service.associado.DeletarAssociadoService;
import br.com.api.assembleia.service.associado.ListarAssociadoService;
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
@RequestMapping("/associado")
@Tag(name = "Associado", description = "Cadastro, listagem, busca e delete de associado.")
public class AssociadoController {

    private final CadastrarAssociadoService cadastrarAssociadoService;

    private final ListarAssociadoService listarAssociadoService;

    private final BuscarAssociadoPorIdService buscarAssociadoPorIdService;

    private final DeletarAssociadoService deletarAssociadoService;

    public AssociadoController(CadastrarAssociadoService cadastrarAssociadoService,
                               ListarAssociadoService listarAssociadoService,
                               BuscarAssociadoPorIdService buscarAssociadoPorIdService,
                               DeletarAssociadoService deletarAssociadoService){
        this.cadastrarAssociadoService = cadastrarAssociadoService;
        this.listarAssociadoService = listarAssociadoService;
        this.buscarAssociadoPorIdService = buscarAssociadoPorIdService;
        this.deletarAssociadoService = deletarAssociadoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar associado", description = "Cadastro de associado passando nome e cpf.")
    @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosDetalhamentoAssociadoDto.class))})
    public ResponseEntity cadastrar (@RequestBody DadosCadastroAssociadoDto dados){
        return cadastrarAssociadoService.cadastrar(dados);
    }

    @GetMapping
    @Operation(summary = "Listar associados", description = "Listagem de associados paginado com 10 associados por pagina ordenado pelo ID.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosListagemAssociadoDto.class))})
    public ResponseEntity<Page<DadosListagemAssociadoDto>> listar (@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return listarAssociadoService.listar(paginacao);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar um associado", description = "Busca um associado passando o seu ID")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosDetalhamentoAssociadoDto.class))})
    public ResponseEntity buscarPorId(@PathVariable Long id){
        return buscarAssociadoPorIdService.buscarPorId(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um associado", description = "Deletar associado por ID.")
    public ResponseEntity deletar(@PathVariable Long id){
        return deletarAssociadoService.deletar(id);
    }
}
