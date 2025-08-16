package com.rafaelmiranda.gestaoclientes.service;

import com.rafaelmiranda.gestaoclientes.model.Cliente;
import com.rafaelmiranda.gestaoclientes.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente novoCliente) {
        Cliente existente = buscarPorId(id);
        existente.setNome(novoCliente.getNome());
        existente.setEmail(novoCliente.getEmail());
        existente.setTelefone(novoCliente.getTelefone());
        return clienteRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }
}

