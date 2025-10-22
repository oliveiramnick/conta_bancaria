package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.GerenteDTO;
import com.example.conta_bancaria.domain.entity.Gerente;
import com.example.conta_bancaria.domain.enums.Role;
import com.example.conta_bancaria.domain.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GerenteService {
    private final GerenteRepository gerenteRepository;

    private final PasswordEncoder encoder;

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public List<GerenteDTO> listarTodosGerentes() {
        return gerenteRepository.findAll().stream()
                .map(GerenteDTO::fromEntity)
                .toList();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE")
    public GerenteDTO cadastrarGerente(GerenteDTO dto) {
        Gerente entity = dto.toEntity();
        entity.setSenha(encoder.encode(dto.senha()));
        entity.setRole(Role.CLIENTE);
        gerenteRepository.save(entity);
        return GerenteDTO.fromEntity(entity);
    }
}
