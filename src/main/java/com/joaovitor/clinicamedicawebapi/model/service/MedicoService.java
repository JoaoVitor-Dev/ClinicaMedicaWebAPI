package com.joaovitor.clinicamedicawebapi.model.service;


import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return repository.findByEspecialidade(especialidade);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}