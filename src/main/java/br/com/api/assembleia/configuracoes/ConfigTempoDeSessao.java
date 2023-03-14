package br.com.api.assembleia.configuracoes;

import br.com.api.assembleia.service.voto.VotoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ConfigTempoDeSessao {
    private final VotoService votoService;

    public ConfigTempoDeSessao(@Lazy VotoService votoService){
        this.votoService = votoService;
    }

    public void tempoDeSessao(Long id, LocalDateTime horarioFim){
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).isAfter(horarioFim)) {
                    votoService.encerrarVotacao(id);
                    timer.cancel();
                }else {
                    timer.cancel();
                    tempoDeSessao(id, horarioFim);
                }
            }
        }, 0, 60000L);

    }

}



