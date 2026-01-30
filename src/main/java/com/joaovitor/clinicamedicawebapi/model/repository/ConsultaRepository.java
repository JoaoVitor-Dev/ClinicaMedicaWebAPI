package com.joaovitor.clinicamedicawebapi.model.repository;

import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    Page<Consulta> findAll(Pageable pageable);

    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
