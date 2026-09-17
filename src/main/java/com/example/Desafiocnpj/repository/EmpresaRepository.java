package com.example.Desafiocnpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Desafiocnpj.model.entity.Empresa;

@Repository 
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

}
