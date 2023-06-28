package com.example.apiAlura.domain.medico;

import com.example.apiAlura.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
    @NotNull
    Long id, 
    String nome, 
    String telefone, 
    DadosEndereco endereco) {
    
}
