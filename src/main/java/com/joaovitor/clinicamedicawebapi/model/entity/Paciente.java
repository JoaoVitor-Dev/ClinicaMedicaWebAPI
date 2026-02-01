package com.joaovitor.clinicamedicawebapi.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Paciente extends Pessoa {

    private String numeroCarteirinha;

    private String convenio;

    public Paciente(Long id, String nome, String cpf, String telefone, String email, Boolean ativo, String numeroCarteirinha, String convenio) {
        super(id, nome, cpf, telefone, email, ativo);
        this.numeroCarteirinha = numeroCarteirinha;
        this.convenio = convenio;
    }

    public Paciente(String numeroCarteirinha, String convenio) {
        this.numeroCarteirinha = numeroCarteirinha;
        this.convenio = convenio;
    }

    public Paciente() {

    }

    public void atualizarCom(Paciente outro) {
        if (outro.getNome() != null) this.setNome(outro.getNome());
        if (outro.getCpf() != null) this.setCpf(outro.getCpf());
        if (outro.getEmail() != null) this.setEmail(outro.getEmail());
        if (outro.getTelefone() != null) this.setTelefone(outro.getTelefone());
        if (outro.getNumeroCarteirinha() != null) this.setNumeroCarteirinha(outro.getNumeroCarteirinha());
        if (outro.getConvenio() != null) this.setConvenio(outro.getConvenio());
        if (outro.getAtivo() != null) this.setAtivo(outro.getAtivo());
    }

    public String getNumeroCarteirinha() {
        return numeroCarteirinha;
    }

    public void setNumeroCarteirinha(String numeroCarteirinha) {
        this.numeroCarteirinha = numeroCarteirinha;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
}
