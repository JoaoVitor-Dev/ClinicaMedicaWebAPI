package com.joaovitor.clinicamedicawebapi.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Medico extends Pessoa {

    private String crm;

    private String especialidade;

    public Medico(Long id, String nome, String cpf, String telefone, String email, String crm, String especialidade) {
        super(id, nome, cpf, telefone, email);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, String especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {

    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
