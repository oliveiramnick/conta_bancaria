package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.ClienteDTO;
import com.example.conta_bancaria.aplication.service.ClienteService;
import com.example.conta_bancaria.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarClientePorId(@PathVariable String id) {
        return clienteService.buscarClientePorId(id);
    }

    @PostMapping("/{id}")
    public ClienteDTO atualizarCliente(@PathVariable String id, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.atualizarCliente(id, clienteDTO);
    }

    @PostMapping
    public ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.salvarCliente(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable String id) {
        clienteService.deletarCliente(id);
    }
}
