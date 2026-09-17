package com.example.Desafiocnpj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.Desafiocnpj.model.dto.SerasaApiResponse;

@Service
public class SerasaService {
    private final RestClient restClient = RestClient.create();

    public SerasaApiResponse buscaCnpj(String cnpj) {
        String url = "https://api.serasaexperian.com.br/pme/hub-pesquisa/v1/business/" + cnpj;
        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(SerasaApiResponse.class);
        } catch (Exception e) {
            return null;
        }
    }
}