package com.example.Desafiocnpj.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Empresa {
    @Id
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String situacaoCadastral;
    private String uf;
    private String municipio;
    private String naturezaJuridica;
    private String dataInicioAtividade;
}
