package com.example.Desafiocnpj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.Desafiocnpj.model.dto.BrasilApiResponse;

@Service
public class CnpjService {
    RestClient restClient = RestClient.create();

    public BrasilApiResponse buscaCnpj(String cnpj) {
        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(BrasilApiResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

}
