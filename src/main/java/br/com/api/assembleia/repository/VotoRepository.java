package br.com.api.assembleia.repository;

import br.com.api.assembleia.model.Voto;
import br.com.api.assembleia.model.VotoDados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, VotoDados> {
}
