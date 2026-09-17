package com.example.Desafiocnpj.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BrasilApiResponse(
        String cnpj,
        String uf,
        String municipio,
        String razaoSocial,
        String nomeFantasia,
        int situacaoCadastral,
        String naturezaJuridica,
        String dataInicioAtividade) {
}