package com.example.Desafiocnpj.model.dto;

public record SerasaApiResponse(
        String businessDocument,
        String businessName,
        String tradeName,
        String businessStatus,
        String foundationDate,
        Address address,
        LegalNature legalNature) {
    public record Address(
            String city,
            String state) {
    }

    public record LegalNature(
            String description) {
    }
}