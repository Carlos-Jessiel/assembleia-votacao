package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.voto.DadosVotacaoDto;
import br.com.api.assembleia.service.voto.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assembleia/voto")
@Tag(name = "Voto", description = "Cadastro de voto em pauta.")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService){
        this.votoService = votoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar voto", description = "Cadastro de voto em pauta passando o ID da pauta, ID do associado e a escolha do voto sendo SIM/NAO")
    @ApiResponse(responseCode = "201")
    public ResponseEntity votar(@RequestBody DadosVotacaoDto dados){
        return votoService.votar(dados);
    }
}
