package com.joaovitor.clinicamedicawebapi.model.repository;

import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);

    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}

