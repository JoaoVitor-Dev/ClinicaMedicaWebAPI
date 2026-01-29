package com.joaovitor.clinicamedicawebapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.service.ConsultaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Consulta", description = "Operações de Consulta Médica")
@RestController
@RequestMapping("/api/consulta")
public class ConsultaController extends BaseController{
    
    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }
    
    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<Page<Consulta>>> listar(Pageable pageable) {
        return okPage(service.listarTodos(pageable));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Consulta consulta) {    
        return created(service.salvar(consulta));
    }

    @GetMapping("/porMedico/{id}")
    public ResponseEntity<?> buscarPorMedico(@PathVariable Long id) {
        return ok(service.buscarPorMedico(id));
    }  

    @GetMapping("/porPaciente/{id}")
    public ResponseEntity<?> buscarPorPaciente(@PathVariable Long id) {
        return ok(service.buscarPorPaciente(id));
    }
}
