package com.joaovitor.clinicamedicawebapi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.dto.ConsultaDto;
import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import com.joaovitor.clinicamedicawebapi.model.service.ConsultaService;
import com.joaovitor.clinicamedicawebapi.model.service.MedicoService;
import com.joaovitor.clinicamedicawebapi.model.service.PacienteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Consulta", description = "Operações de Consulta Médica")
@RestController
@RequestMapping("/api/consulta")
public class ConsultaController extends BaseController{
    
    private final ConsultaService service;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public ConsultaController(ConsultaService service, MedicoService medicoService, PacienteService pacienteService) {
        this.service = service;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }
    
    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<Page<Consulta>>> listar(@ParameterObject Pageable pageable) {
        return okPage(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ConsultaDto consulta) {   

        Medico medico = medicoService.buscarPorId(consulta.medicoId());
        if (medico == null) {
            return notFound("Médico não encontrado com ID: " + consulta.medicoId());
        }

        Paciente paciente = pacienteService.buscarPorId(consulta.pacienteId());
        if (paciente == null) {
            return notFound("Paciente não encontrado com ID: " + consulta.pacienteId());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dataHora = LocalDate
            .parse(consulta.dataHora(), formatter)
            .atStartOfDay();

        return created(service.salvar(new Consulta(
            paciente,
            medico,
            dataHora,
            consulta.observacoes()
        )));
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
