package com.example.apiAlura.domain.medico;

import com.example.apiAlura.domain.endereco.Endereco;

public record DadosDetalhadoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhadoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
    
}
