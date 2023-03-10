package br.com.api.assembleia.configuracoes;

import br.com.api.assembleia.model.Pauta;
import br.com.api.assembleia.model.enums.Status;
import br.com.api.assembleia.repository.PautaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ConfigTempoDeSessao {

    private final PautaRepository reposito;

    public ConfigTempoDeSessao(PautaRepository reposito){
        this.reposito = reposito;
    }

    public void tempoPauta(Long id) {
        Pauta pauta = reposito.getReferenceById(id);
        timer(pauta);
    }

    public void tempoVoto(Pauta pauta) {
        timer(pauta);
    }

    public void timer(Pauta pauta){
        Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).isAfter(pauta.getHorarioFim())) {
                        pauta.setStatus(Status.VOTACAO_FINALIZADA);
                        timer.cancel();
                        reposito.save(pauta);
                        System.out.println("finalizado");
                    }else {
                        timer.cancel();
                        timer(pauta);
                    }
                }
            }, 0, 60000L);

    }


}



