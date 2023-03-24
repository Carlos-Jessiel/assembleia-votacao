package br.com.api.assembleia.model;

import br.com.api.assembleia.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Pauta")
@Table(name = "pautas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private LocalDateTime horarioInicio;

    private LocalDateTime horarioFim;

    private Integer votoSim;

    private Integer votoNao;

    @Enumerated(EnumType.STRING)
    private Status status;
}
