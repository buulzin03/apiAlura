package com.example.apiAlura.domain.paciente;

import com.example.apiAlura.domain.endereco.Endereco;

public record DadosDetalhadoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DadosDetalhadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
