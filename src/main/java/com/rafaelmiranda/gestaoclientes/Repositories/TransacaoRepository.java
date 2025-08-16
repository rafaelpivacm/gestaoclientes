package com.rafaelmiranda.gestaoclientes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelmiranda.gestaoclientes.model.Transacao;



@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
