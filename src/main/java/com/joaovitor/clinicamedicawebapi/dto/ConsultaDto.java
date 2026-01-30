package com.joaovitor.clinicamedicawebapi.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultaDto(
    Long pacienteId,
    Long medicoId,
    String dataHora,
    String observacoes
) {}
