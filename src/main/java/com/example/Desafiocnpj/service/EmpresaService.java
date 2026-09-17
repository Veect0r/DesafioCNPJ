package com.example.Desafiocnpj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Desafiocnpj.model.api.BrasilApiResponse;
import com.example.Desafiocnpj.model.api.SerasaApiResponse;
import com.example.Desafiocnpj.model.entity.Empresa;
import com.example.Desafiocnpj.repository.EmpresaRepository;
import com.example.Desafiocnpj.validation.CnpjValidation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpresaService {
    private final CnpjService cnpjService;
    private final SerasaService serasaService;
    private final EmpresaRepository empresaRepository;
    private final CnpjValidation cnpjValidation;

    public Empresa buscaEmpresa(String cnpj) {
        String cnpjLimpo = cnpjValidation.limparCnpj(cnpj);

        if (!cnpjValidation.isValidCnpj(cnpjLimpo)) {
            return null;
        }

        Optional<Empresa> empresaExistente = empresaRepository.findById(cnpjLimpo);
        if (empresaExistente.isPresent()) {
            return empresaExistente.get();
        }

        SerasaApiResponse serasaResponse = serasaService.buscaCnpj(cnpjLimpo);

        if (serasaResponse != null && serasaResponse.businessDocument() != null) {
            Empresa empresa = new Empresa(
                    serasaResponse.businessDocument(),
                    serasaResponse.businessName(),
                    serasaResponse.tradeName(),
                    serasaResponse.businessStatus(),
                    serasaResponse.address().state(),
                    serasaResponse.address().city(),
                    serasaResponse.legalNature().description(),
                    serasaResponse.foundationDate());
            empresaRepository.save(empresa);
            return empresa;
        }

        BrasilApiResponse response = cnpjService.buscaCnpj(cnpjLimpo);

        if (response == null || response.cnpj() == null) {
            return null;
        }

        String situacao = response.situacaoCadastral() == 2 ? "ATIVA" : "INATIVA";

        Empresa empresa = new Empresa(
                response.cnpj(),
                response.razaoSocial(),
                response.nomeFantasia(),
                situacao,
                response.uf(),
                response.municipio(),
                response.naturezaJuridica(),
                response.dataInicioAtividade());

        empresaRepository.save(empresa);
        return empresa;
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }
}