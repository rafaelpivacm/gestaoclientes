package com.rafaelmiranda.gestaoclientes.service;

import org.springframework.stereotype.Service;

import com.rafaelmiranda.gestaoclientes.model.Vendedor;
import com.rafaelmiranda.gestaoclientes.repository.VendedorRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public Vendedor buscarPorId(Long id) {
        return vendedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));
    }

    public Vendedor salvar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor atualizar(Long id, Vendedor novoVendedor) {
        Vendedor existente = buscarPorId(id);
        existente.setNome(novoVendedor.getNome());
        existente.setEmail(novoVendedor.getEmail());
        existente.setTelefone(novoVendedor.getTelefone());
        return vendedorRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new EntityNotFoundException("Vendedor não encontrado");
        }
        vendedorRepository.deleteById(id);
    }
}
