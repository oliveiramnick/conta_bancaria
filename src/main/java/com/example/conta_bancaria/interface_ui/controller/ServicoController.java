package com.example.conta_bancaria.interface_ui.controller;

import com.example.conta_bancaria.aplication.dto.ClienteRegistroDTO;
import com.example.conta_bancaria.aplication.dto.ServicoDTO;
import com.example.conta_bancaria.aplication.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Serviços", description = "Gerenciamento de serviços da conta bancária")
@RestController
@RequestMapping("/api/cliente")
public class ServicoController {
    private final ClienteService service;
    private final ClienteRegistroDTO ;

    public ServicoController(ClienteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastrar um novo cliente",
            description = "Adiciona um novo cliente à base de dados após validações de dados pessoais",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ServicoDTO.class),
                            examples = @ExampleObject(name = "Exemplo válido", value = """
                                        {
                                          "nome": "Guilherme",
                                          "cpf": "usuario@email.com",
                                          "email": "123456",
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Nome incompleto", value = "\"O nome deve ser completo\""),
                                            @ExampleObject(name = "O CPF é obrigatório", value = "\"O CPF não pode ser vazio\""),
                                            @ExampleObject(name = "O e-mail é obrigatório", value = "\"O e-mail não pode ser vazio\""),
                                            @ExampleObject(name = "A senha é obrigatória", value = "\"A senha não pode ser vazia\"")
                                    }
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ServicoDTO> criar(@Valid @org.springframework.web.bind.annotation.RequestBody ServicoDTO dto) {
        return ResponseEntity
                .status(201)
                .body(service.registrarClienteOuAnexarConta(dto));
    }

    @Operation(
            summary = "Listar todos os clientes",
            description = "Retorna todos os clientes cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<ServicoDTO>>listarClientesAtivos() {
        return ResponseEntity
                .ok(service.listarClientesAtivos());

    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna um cliente existente a partir do seu ID",
            parameters = {
                    @Parameter(name = "id", description = "ID do cliente a ser buscado", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Cliente com ID 99 não encontrado.\"")
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar um serviço",
            description = "Atualiza os dados de um serviço existente com novas informações",
            parameters = {
                    @Parameter(name = "id", description = "ID do serviço a ser atualizado", example = "1")
            },
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ServicoDTO.class),
                            examples = @ExampleObject(name = "Exemplo de atualização", value = """
                        {
                          "descricao": "Revisão completa",
                          "preco": 200.0,
                          "dataInicio": "2025-08-01",
                          "dataFim": "2025-08-10"
                        }
                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Preço inválido", value = "\"Preço mínimo do serviço deve ser R$ 50,00\""),
                                            @ExampleObject(name = "Duração excedida", value = "\"Duração do serviço não pode exceder 30 dias\"")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Serviço não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Serviço com ID 99 não encontrado.\"")
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable Long id, @Valid @org.springframework.web.bind.annotation.RequestBody ServicoDTO dto) {
        return ResponseEntity
                .ok(service.atualizar(id, dto));
    }

    @Operation(
            summary = "Deletar um serviço",
            description = "Remove um serviço da base de dados a partir do seu ID",
            parameters = {
                    @Parameter(name = "id", description = "ID do serviço a ser deletado", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Serviço removido com sucesso"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Serviço não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Serviço com ID 99 não encontrado.\"")
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
