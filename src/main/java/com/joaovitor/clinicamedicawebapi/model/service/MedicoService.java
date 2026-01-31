package com.joaovitor.clinicamedicawebapi.model.service;

import com.joaovitor.clinicamedicawebapi.exception.RegraNegocioException;
import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import com.joaovitor.clinicamedicawebapi.model.entity.Medico;
import com.joaovitor.clinicamedicawebapi.model.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico salvar(Medico medico) {
        validarCpfDuplicado(medico);
        validarCrmDuplicado(medico);
        return repository.save(medico);
    }

    public Page<Medico> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Medico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return repository.findByEspecialidade(especialidade);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private void validarCpfDuplicado(Medico medico) {
        Optional<Medico> medicoExistente = repository.findByCpf(medico.getCpf());

        if (medicoExistente.isPresent()) {
            if (medico.getId() == null || !medicoExistente.get().getId().equals(medico.getId())) {
                throw new RegraNegocioException("Já existe um médico cadastrado com o CPF: " + medico.getCpf());
            }
        }
    }

    private void validarCrmDuplicado(Medico medico) {
        if (medico.getCrm() == null || medico.getCrm().isBlank()) {
            return;
        }

        Optional<Medico> medicoExistente = repository.findByCrm(medico.getCrm());

        if (medicoExistente.isPresent()) {
            if (medico.getId() == null || !medicoExistente.get().getId().equals(medico.getId())) {
                throw new RegraNegocioException("Já existe um médico cadastrado com o CRM: " + medico.getCrm());
            }
        }
    }
}