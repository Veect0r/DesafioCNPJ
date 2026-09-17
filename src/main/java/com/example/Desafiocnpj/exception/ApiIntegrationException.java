package com.example.Desafiocnpj.exception;

public class ApiIntegrationException extends RuntimeException {
    private final String provider;
    private final Integer statusCode;

    public ApiIntegrationException(String provider, Integer statusCode, Throwable cause) {
        super("Falha ao consultar a API " + provider, cause);
        this.provider = provider;
        this.statusCode = statusCode;
    }

    public String getProvider() {
        return provider;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}