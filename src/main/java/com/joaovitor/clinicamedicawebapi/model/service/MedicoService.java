package com.joaovitor.clinicamedicawebapi.model.service;

import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import com.joaovitor.clinicamedicawebapi.model.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public Page<Medico> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Medico buscarPorId(Long id) {

        System.out.println("Buscando médico com ID: " + id);

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