package com.example.Desafiocnpj.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CnpjRequest(
	@NotBlank(message = "Digite um CNPJ para pesquisar.")
	String cnpj) {

	public CnpjRequest {
		cnpj = cnpj == null ? null : cnpj.trim();
	}
}

