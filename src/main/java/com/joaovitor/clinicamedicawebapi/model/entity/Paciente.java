package com.joaovitor.clinicamedicawebapi.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Paciente extends Pessoa {

    private String numeroCarteirinha;

    private String convenio;

    public Paciente(Long id, String nome, String cpf, String telefone, String email, String numeroCarteirinha, String convenio) {
        super(id, nome, cpf, telefone, email);
        this.numeroCarteirinha = numeroCarteirinha;
        this.convenio = convenio;
    }

    public Paciente(String numeroCarteirinha, String convenio) {
        this.numeroCarteirinha = numeroCarteirinha;
        this.convenio = convenio;
    }

    public Paciente() {

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
