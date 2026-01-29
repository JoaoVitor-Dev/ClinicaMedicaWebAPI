package com.joaovitor.clinicamedicawebapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Clínica Médica Web API", version = "1.0", description = "API para gerenciamento de clinica medica"))
public class ClinicaMedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaMedicaApplication.class, args);
	}
}
