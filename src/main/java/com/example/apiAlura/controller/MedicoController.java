package com.example.apiAlura.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.apiAlura.domain.medico.DadosAtualizaMedico;
import com.example.apiAlura.domain.medico.DadosCadastroMedico;
import com.example.apiAlura.domain.medico.DadosDetalhadoMedico;
import com.example.apiAlura.domain.medico.DadosListagemMedico;
import com.example.apiAlura.domain.medico.Medico;
import com.example.apiAlura.domain.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> lista(@PageableDefault(size = 10, sort={"id"}) Pageable paginacao) {
        
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizaMedico dados){
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhadoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleta(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhadoMedico(medico));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.ativar();

        return ResponseEntity.ok(new DadosDetalhadoMedico(medico));
    }
}
