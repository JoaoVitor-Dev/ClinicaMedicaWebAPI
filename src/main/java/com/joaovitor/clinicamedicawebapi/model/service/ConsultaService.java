package com.joaovitor.clinicamedicawebapi.model.service;


import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import com.joaovitor.clinicamedicawebapi.model.repository.ConsultaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public Page<Consulta> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Consulta> buscarPorPaciente(Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    public List<Consulta> buscarPorMedico(Long medicoId) {
        return repository.findByMedicoId(medicoId);
    }

    public List<Consulta> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataHoraBetween(inicio, fim);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}