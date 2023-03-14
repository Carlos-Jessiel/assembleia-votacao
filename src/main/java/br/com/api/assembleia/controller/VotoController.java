package br.com.api.assembleia.controller;

import br.com.api.assembleia.dto.voto.DadosVotacaoDto;
import br.com.api.assembleia.service.voto.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assembleia/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService){
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity votar(@RequestBody DadosVotacaoDto dados){
        return votoService.votar(dados);
    }
}
