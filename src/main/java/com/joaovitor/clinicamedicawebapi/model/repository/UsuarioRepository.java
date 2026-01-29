package com.joaovitor.clinicamedicawebapi.model.repository;

import com.joaovitor.clinicamedicawebapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}