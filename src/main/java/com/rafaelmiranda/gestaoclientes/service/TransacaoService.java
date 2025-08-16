package com.rafaelmiranda.gestaoclientes.service;

import org.springframework.stereotype.Service;

import com.rafaelmiranda.gestaoclientes.model.Transacao;
import com.rafaelmiranda.gestaoclientes.repository.TransacaoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    }

    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao novaTransacao) {
    Transacao existente = buscarPorId(id);
    existente.setCliente(novaTransacao.getCliente());
    existente.setVendedor(novaTransacao.getVendedor());
    existente.setValor(novaTransacao.getValor());
    existente.setTipo(novaTransacao.getTipo());
    existente.setDataTransacao(novaTransacao.getDataTransacao());
    return transacaoRepository.save(existente); 
    }

    public void deletar(Long id) {
        if (!transacaoRepository.existsById(id)) {
        throw new EntityNotFoundException("Transação não encontrada");
        }
        transacaoRepository.deleteById(id);
    }
}
