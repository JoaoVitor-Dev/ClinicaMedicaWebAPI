package com.joaovitor.clinicamedicawebapi.dto;

public record ConsultaDto(
    Long pacienteId,
    Long medicoId,
    String dataHora,
    String observacoes
) {}
