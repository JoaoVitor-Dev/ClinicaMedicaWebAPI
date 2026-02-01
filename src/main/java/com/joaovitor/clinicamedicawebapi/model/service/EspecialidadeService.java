package com.joaovitor.clinicamedicawebapi.model.service;

import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Especialidade;
import com.joaovitor.clinicamedicawebapi.model.repository.EspecialidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository repository;

    public EspecialidadeService(EspecialidadeRepository repository) {
        this.repository = repository;
    }

    public Especialidade salvar(Especialidade especialidade) {
        return repository.save(especialidade);
    }

    public Page<Especialidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Especialidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}