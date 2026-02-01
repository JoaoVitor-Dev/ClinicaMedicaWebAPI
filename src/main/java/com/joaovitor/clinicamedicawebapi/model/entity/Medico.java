package com.joaovitor.clinicamedicawebapi.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Medico extends Pessoa {

    private String crm;

    private String especialidade;

    public Medico(Long id, String nome, String cpf, String telefone, String email, Boolean ativo, String crm, String especialidade) {
        super(id, nome, cpf, telefone, email, ativo);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, String especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {

    }

    public void atualizarCom(Medico outro) {
        if (outro.getNome() != null) this.setNome(outro.getNome());
        if (outro.getCpf() != null) this.setCpf(outro.getCpf());
        if (outro.getEmail() != null) this.setEmail(outro.getEmail());
        if (outro.getTelefone() != null) this.setTelefone(outro.getTelefone());
        if (outro.getCrm() != null) this.setCrm(outro.getCrm());
        if (outro.getEspecialidade() != null) this.setEspecialidade(outro.getEspecialidade());
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
