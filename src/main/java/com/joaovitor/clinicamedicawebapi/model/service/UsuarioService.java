package com.joaovitor.clinicamedicawebapi.model.service;

import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Usuario;
import com.joaovitor.clinicamedicawebapi.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
}
