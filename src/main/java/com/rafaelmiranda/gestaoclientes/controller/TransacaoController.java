package com.rafaelmiranda.gestaoclientes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelmiranda.gestaoclientes.model.Transacao;
import com.rafaelmiranda.gestaoclientes.service.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Transacao buscarPorId(@PathVariable Long id) {
        return transacaoService.buscarPorId(id);
    }

    @PostMapping
    public Transacao criarTransacao(@RequestBody @Valid Transacao transacao) {
        return transacaoService.salvar(transacao);
    }

    @PutMapping("/{id}")
    public Transacao atualizarTransacao(@PathVariable Long id, @RequestBody @Valid Transacao transacao) {
        return transacaoService.atualizar(id, transacao);
    }

    @DeleteMapping("/{id}")
    public void deletarTransacao(@PathVariable Long id) {
        transacaoService.deletar(id);
    }

    

}
