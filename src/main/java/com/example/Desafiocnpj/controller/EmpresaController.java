package com.example.Desafiocnpj.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Desafiocnpj.model.dto.CnpjRequest;
import com.example.Desafiocnpj.model.dto.ErroResponse;
import com.example.Desafiocnpj.model.entity.Empresa;
import com.example.Desafiocnpj.service.EmpresaService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor 
@RestController
public class EmpresaController {
    private final EmpresaService empresaService;

    @PostMapping("/cnpj")
    public ResponseEntity<Object> buscaEmpresa(@RequestBody CnpjRequest request) {
        Empresa empresa = empresaService.buscaEmpresa(request.cnpj());
        if (empresa != null) {
            return ResponseEntity.status(201).body(empresa);
        }
        return ResponseEntity.status(404).body(new ErroResponse("CNPJ não encontrado!"));
    }

    @GetMapping("/cnpj")
    public ResponseEntity<List<Empresa>> listaEmpresas() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Object> buscaPorUrl(@PathVariable String cnpj) {
        Empresa empresa = empresaService.buscaEmpresa(cnpj);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.status(404).body(new ErroResponse("CNPJ não encontrado!"));
    }
}
