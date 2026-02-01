package com.joaovitor.clinicamedicawebapi.model.service;

import com.joaovitor.clinicamedicawebapi.exception.RegraNegocioException;
import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import com.joaovitor.clinicamedicawebapi.model.repository.ConsultaRepository;
import com.joaovitor.clinicamedicawebapi.model.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final ConsultaRepository consultaRepository;

    public PacienteService(PacienteRepository repository, ConsultaRepository consultaRepository) {
        this.repository = repository;
        this.consultaRepository = consultaRepository;
    }

    public Paciente salvar(Paciente paciente) {
        validarCpfDuplicado(paciente);
        validarNumeroCarteirinhaDuplicado(paciente);
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
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(false);
        repository.save(paciente);
    }

    private void validarCpfDuplicado(Paciente paciente) {
        Optional<Paciente> pacienteExistente = repository.findByCpf(paciente.getCpf());

        if (pacienteExistente.isPresent()) {
            if (paciente.getId() == null || !pacienteExistente.get().getId().equals(paciente.getId())) {
                throw new RegraNegocioException("Já existe um paciente cadastrado com o CPF: " + paciente.getCpf());
            }
        }
    }

    private void validarNumeroCarteirinhaDuplicado(Paciente paciente) {
        if (paciente.getNumeroCarteirinha() == null || paciente.getNumeroCarteirinha().isBlank()) {
            return;
        }

        Optional<Paciente> pacienteExistente = repository.findByNumeroCarteirinha(paciente.getNumeroCarteirinha());

        if (pacienteExistente.isPresent()) {
            if (paciente.getId() == null || !pacienteExistente.get().getId().equals(paciente.getId())) {
                throw new RegraNegocioException("Já existe um paciente cadastrado com o número de carteirinha: " + paciente.getNumeroCarteirinha());
            }
        }
    }
}