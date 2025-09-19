package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.ContaDTO;
import com.example.conta_bancaria.aplication.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Java
@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @GetMapping
    public List<ContaDTO> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public ContaDTO buscarContaPorId(@PathVariable String id) {
        return contaService.buscarContaPorId(id);
    }

    @PostMapping("/{id}")
    public ContaDTO atualizarConta(@PathVariable String id, @RequestBody ContaDTO contaDTO) {
        return contaService.atualizarConta(id, contaDTO);
    }

    @PostMapping
    public ContaDTO salvarConta(@RequestBody ContaDTO contaDTO) {
        return contaService.salvarConta(contaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable String id) {
        contaService.deletarConta(id);
    }
}

