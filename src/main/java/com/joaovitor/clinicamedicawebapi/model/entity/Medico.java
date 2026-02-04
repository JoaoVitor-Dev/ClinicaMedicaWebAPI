package com.joaovitor.clinicamedicawebapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico extends Pessoa {

    private String crm;

    @ManyToMany
    @JoinTable(
            name = "medico_especialidade",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    @JsonManagedReference  // Serializa a lista de especialidades
    private List<Especialidade> especialidades = new ArrayList<>();

    public Medico(Long id, String nome, String cpf, String telefone, String email, Boolean ativo, String crm) {
        super(id, nome, cpf, telefone, email, ativo);
        this.crm = crm;
    }

    public Medico(String crm) {
        this.crm = crm;
    }

    public Medico() {

    }

    public void atualizarCom(Medico outro) {
        if (outro.getNome() != null) this.setNome(outro.getNome());
        if (outro.getCpf() != null) this.setCpf(outro.getCpf());
        if (outro.getEmail() != null) this.setEmail(outro.getEmail());
        if (outro.getTelefone() != null) this.setTelefone(outro.getTelefone());
        if (outro.getCrm() != null) this.setCrm(outro.getCrm());
    }

    // Métodos auxiliares para gerenciar o relacionamento bidirecional
    public void adicionarEspecialidade(Especialidade especialidade) {
        this.especialidades.add(especialidade);
        especialidade.getMedicos().add(this);
    }

    public void removerEspecialidade(Especialidade especialidade) {
        this.especialidades.remove(especialidade);
        especialidade.getMedicos().remove(this);
    }

    // Getters e Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}