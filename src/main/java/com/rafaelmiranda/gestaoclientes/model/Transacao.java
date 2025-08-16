package com.rafaelmiranda.gestaoclientes.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @PrePersist
    public void prePersist() {
        if (this.dataTransacao == null) {
            this.dataTransacao = LocalDateTime.now();
        }
    }

    public enum TipoTransacao {
        VENDA, DESPESA
    }
}

