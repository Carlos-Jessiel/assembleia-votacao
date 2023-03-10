package br.com.api.assembleia.model;

import br.com.api.assembleia.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Pauta")
@Table(name = "pauta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime inicio;

    private LocalDateTime fim;
    private Integer sim;
    private Integer nao;
    @Enumerated(EnumType.STRING)
    private Status status;
}
