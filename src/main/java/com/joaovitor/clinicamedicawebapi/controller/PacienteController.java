package com.joaovitor.clinicamedicawebapi.controller;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import com.joaovitor.clinicamedicawebapi.model.service.PacienteService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Paciente", description = "Operações relacionadas a paciente")
@RestController
@RequestMapping("/api/paciente")
public class PacienteController extends BaseController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping(value = "/todos", produces = "application/json")
    public ResponseEntity<ApiResponse<Page<Paciente>>> listar(Pageable pageable) {
        return okPage(service.listarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Paciente paciente) {
        return created(service.salvar(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ok("Paciente excluído com sucesso.");
    }
}