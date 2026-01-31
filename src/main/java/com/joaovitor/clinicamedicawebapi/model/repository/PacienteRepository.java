package com.joaovitor.clinicamedicawebapi.model.repository;

import com.joaovitor.clinicamedicawebapi.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);

    Optional<Paciente> findByNumeroCarteirinha(String numeroCarteirinha);
}