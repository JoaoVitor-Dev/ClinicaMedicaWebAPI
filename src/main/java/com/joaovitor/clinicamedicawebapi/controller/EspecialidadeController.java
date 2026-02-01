package com.joaovitor.clinicamedicawebapi.controller;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.dto.EspecialidadeDto;
import com.joaovitor.clinicamedicawebapi.model.entity.Especialidade;
import com.joaovitor.clinicamedicawebapi.model.service.EspecialidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Especialidade", description = "Operações de Especialidade Médica")
@RestController
@RequestMapping("/api/especialidade")
public class EspecialidadeController extends BaseController {

    private final EspecialidadeService service;

    public EspecialidadeController(EspecialidadeService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<Page<Especialidade>>> listar(@ParameterObject Pageable pageable) {
        return okPage(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Especialidade>> buscarPorId(@PathVariable Long id) {
        return ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Especialidade>> criar(@RequestBody EspecialidadeDto dto) {
        Especialidade especialidade = new Especialidade(dto.nome(), dto.descricao());
        return created(service.salvar(especialidade));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Especialidade>> atualizar(
            @PathVariable Long id,
            @RequestBody EspecialidadeDto dto
    ) {
        Especialidade especialidade = service.buscarPorId(id);

        if (dto.nome() != null) {
            especialidade.setNome(dto.nome());
        }
        if (dto.descricao() != null) {
            especialidade.setDescricao(dto.descricao());
        }

        return ok(service.salvar(especialidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ok("Especialidade excluída com sucesso");
    }
}