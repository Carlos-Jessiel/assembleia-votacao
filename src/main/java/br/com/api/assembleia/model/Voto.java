package br.com.api.assembleia.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity(name = "Voto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @EmbeddedId
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VotoDados id;

    @Enumerated(EnumType.STRING)
    private EscolhaVoto voto;
}
