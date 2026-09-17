package com.example.Desafiocnpj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestClient;

import com.example.Desafiocnpj.exception.ApiIntegrationException;
import com.example.Desafiocnpj.model.api.SerasaApiResponse;

@Service
public class SerasaService {
    private static final String PROVIDER = "Serasa";
    private final RestClient restClient = RestClient.create();

    public SerasaApiResponse buscaCnpj(String cnpj) {
        String url = "https://api.serasaexperian.com.br/pme/hub-pesquisa/v1/business/" + cnpj;
        try {
            return restClient.get().uri(url).retrieve().body(SerasaApiResponse.class);
        } catch (RestClientResponseException exception) {
            if (exception.getStatusCode().value() == 404) {
                return null;
            }
            throw new ApiIntegrationException(PROVIDER, exception.getStatusCode().value(), exception);
        } catch (RestClientException exception) {
            throw new ApiIntegrationException(PROVIDER, null, exception);
        }
    }
}