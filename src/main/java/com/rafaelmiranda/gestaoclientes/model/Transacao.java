package com.rafaelmiranda.gestaoclientes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotNull
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id")
    @NotNull
    private Vendedor vendedor;

    @NotNull
    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private TipoTransacao tipo;

    @Column(name = "data_transacao", nullable = false)
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao that = (Transacao) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

