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

import com.rafaelmiranda.gestaoclientes.model.Vendedor;
import com.rafaelmiranda.gestaoclientes.service.VendedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Vendedor> listarTodos() {
        return vendedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Vendedor buscarPorId(@PathVariable Long id) {
        return vendedorService.buscarPorId(id);
    }

    @PostMapping
    public Vendedor salvar(@RequestBody @Valid Vendedor vendedor) {
        return vendedorService.salvar(vendedor);
    }

    @PutMapping("/{id}")
    public Vendedor atualizar(@PathVariable Long id, @RequestBody @Valid Vendedor vendedor) {
        return vendedorService.atualizar(id, vendedor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        vendedorService.deletar(id);
    }
}
