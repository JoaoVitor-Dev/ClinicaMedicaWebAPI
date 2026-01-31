package com.joaovitor.clinicamedicawebapi.model.repository;

import com.joaovitor.clinicamedicawebapi.model.entity.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    Page<Consulta> findAll(Pageable pageable);

    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataHora = :dataHora")
    Optional<Consulta> findByMedicoIdAndDataHora(@Param("medicoId") Long medicoId,
                                                 @Param("dataHora") LocalDateTime dataHora);
}
