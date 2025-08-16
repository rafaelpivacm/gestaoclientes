package com.rafaelmiranda.gestaoclientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String telefone;

    @NotBlank
    @Column(name = "cpf_cnpj", unique = true, nullable = false)
    private String cpfCnpj;

    private String endereco;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Transacao> transacoes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cliente cliente = (Cliente) object;
        return id != null && id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
