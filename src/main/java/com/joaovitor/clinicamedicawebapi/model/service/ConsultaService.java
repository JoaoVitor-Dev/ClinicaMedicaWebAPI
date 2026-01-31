package com.joaovitor.clinicamedicawebapi.model.service;


import com.joaovitor.clinicamedicawebapi.exception.RegraNegocioException;
import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import com.joaovitor.clinicamedicawebapi.model.repository.ConsultaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public Consulta salvar(Consulta consulta) {

        Optional<Consulta> consultaExistente = repository.findByMedicoIdAndDataHora(
                consulta.getMedico().getId(),
                consulta.getDataHora()
        );

        System.out.println("Consulta existente? " + consultaExistente.isPresent());

        if (consultaExistente.isPresent()) {
            throw new RegraNegocioException(
                    "Já existe uma consulta agendada para este médico neste horário"
            );
        }

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