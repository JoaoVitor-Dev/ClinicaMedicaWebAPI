package com.joaovitor.clinicamedicawebapi.controller;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.service.MedicoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Médico", description = "Operações relacionadas a médico")
@RestController
@RequestMapping("/api/medico")
public class MedicoController extends BaseController {
    
    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }   

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<Page<Medico>>> listar(Pageable pageable) {
        return okPage(service.listarTodos(pageable));
    }   

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Medico medico) {    
        return created(service.salvar(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ok(service.buscarPorId(id));
    }   

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Medico medicoAtual){
        Medico medicoExistente = service.buscarPorId(id);
        if (medicoExistente == null) {
            return notFound("Médico não encontrado com ID: " + id);
        }

        medicoExistente.setNome(medicoAtual.getNome());
        medicoExistente.setEspecialidade(medicoAtual.getEspecialidade());
        medicoExistente.setCrm(medicoAtual.getCrm());

        Medico medicoAtualizado = service.salvar(medicoExistente);

        if (medicoAtualizado == null) {
            return badRequest("Erro ao atualizar o médico com ID: " + id);
        }

        return ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ok("Médico excluído com sucesso.");
    }

}
