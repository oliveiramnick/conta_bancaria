package com.example.conta_bancaria.aplication.dto;

import com.example.conta_bancaria.domain.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ServicoDTO(
        @Schema(description = "ID do cliente", example = "1")
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do Cliente", example = "")
        String nome,

        @NotNull(message = "CPF é obrigatório")
        @Schema(description = "CPF do cliente", example = "000.000.000.00")
        Double cpf,

        @NotNull(message = "Email é obrigatório")
        @Schema(description = "Email do cliente", example = "usuario@email.com")
        LocalDate email,

        @NotNull(message = "Senha é obrigatória")
        @Schema(description = "Senha", example = "123456")
        LocalDate dataFim
) {
    public static ServicoDTO fromEntity(Cliente c) {
        return new ServicoDTO(
                c.getId(),
                c.getDescricao(),
                c.getPreco(),
                c.getDataInicio(),
                c.getDataFim()
        );
    }

    public Servico toEntity() {
        return Servico.builder()
                .id(id)
                .descricao(descricao)
                .preco(preco)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();
    }
) {
}
