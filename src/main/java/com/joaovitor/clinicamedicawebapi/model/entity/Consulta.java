package com.joaovitor.clinicamedicawebapi.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private LocalDateTime dataHora;

    private String observacoes;

    public Consulta(Long id, Paciente paciente, Medico medico, LocalDateTime dataHora, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
    }

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String observacoes) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
    }

    public Consulta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}