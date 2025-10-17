package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.GerenteDTO;
import com.example.conta_bancaria.aplication.service.GerenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
@RequiredArgsConstructor
public class GerenteController {
    private final GerenteService service;

    @GetMapping
    public ResponseEntity<List<GerenteDTO>> listarTodosGerentes() {
        List<GerenteDTO> gerentes = service.listarTodosGerentes();
        return ResponseEntity.ok(gerentes);
    }

    @PostMapping
    public ResponseEntity<GerenteDTO> cadastrarProfessor(@RequestBody GerenteDTO dto) {
        GerenteDTO gerenteCriado = service.cadastrarGerente(dto);
        return ResponseEntity.ok(gerenteCriado);
    }

}
