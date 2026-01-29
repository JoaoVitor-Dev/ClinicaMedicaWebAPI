package com.joaovitor.clinicamedicawebapi.model.service;


import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import com.joaovitor.clinicamedicawebapi.model.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Page<Paciente> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }

    public Paciente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + cpf));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
