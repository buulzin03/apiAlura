package com.example.apiAlura.domain.paciente;

import com.example.apiAlura.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
    
}
