package com.example.conta_bancaria.aplication.service;

import com.example.conta_bancaria.aplication.dto.GerenteDTO;
import com.example.conta_bancaria.domain.entity.Gerente;
import com.example.conta_bancaria.domain.enums.Role;
import com.example.conta_bancaria.domain.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GerenteService {
    private final GerenteRepository gerenteRepository;

    private final PasswordEncoder encoder;

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public List<GerenteDTO> listarTodosProfessores() {
        return Repository.findAll().stream()
                .map(GerenteDTO::fromEntity)
                .toList();
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    public GerenteDTO cadastrarGerente(GerenteDTO dto) {
        Gerente entity = dto.toEntity();
        entity.setSenha(encoder.encode(dto.senha()));
        entity.setRole(Role.GERENTE);
        gerenteRepository.save(entity);
        return GerenteDTO.fromEntity(entity);
    }
}
