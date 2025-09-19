package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.example.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.example.conta_bancaria.aplication.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        return service.registrarClienteOuAnexarConta(dto);
    }

    @GetMapping
    public List<ClienteRegistroDTO> listarClientes() {
        return service.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteRegistroDTO buscarClientePorId(@PathVariable String id) {
        return service.buscarClientePorId(id);
    }



    @PostMapping
    public ClienteRegistroDTO salvarCliente(@RequestBody ClienteRegistroDTO clienteDTO) {
        return service.salvarCliente(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable String id) {
        service.deletarCliente(id);
    }
}
