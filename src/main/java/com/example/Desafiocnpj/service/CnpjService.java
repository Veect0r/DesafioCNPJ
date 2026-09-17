package com.example.Desafiocnpj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestClient;

import com.example.Desafiocnpj.exception.ApiIntegrationException;
import com.example.Desafiocnpj.model.api.BrasilApiResponse;

@Service
public class CnpjService {
    private static final String PROVIDER = "BrasilAPI";
    private final RestClient restClient = RestClient.create();

    public BrasilApiResponse buscaCnpj(String cnpj) {
        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
        try {
            return restClient.get().uri(url).retrieve().body(BrasilApiResponse.class);
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
