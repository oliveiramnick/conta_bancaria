package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.example.conta_bancaria.aplication.dto.ClienteResponseDTO;
import com.example.conta_bancaria.aplication.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity <ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO novoCliente = service.registrarClienteOuAnexarConta(dto);
        return ResponseEntity.created(
                URI.create("api/cliente/cpf"+novoCliente.cpf())
        ).body(novoCliente);
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>>listarClientesAtivos(){
        return ResponseEntity.ok(service.listarClientesAtivos());
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClienteAtivoPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarClienteAtivoPorCpf(cpf));
    }
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteRegistroDTO dto){
        return ResponseEntity.ok(service.atualizarCliente(cpf,dto));
    }
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }
}
