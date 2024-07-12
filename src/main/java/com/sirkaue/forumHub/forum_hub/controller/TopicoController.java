package com.sirkaue.forumHub.forum_hub.controller;

import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosAtualizacaoTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosListagemTopico;
import com.sirkaue.forumHub.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoTopico dadosDetalhamentoTopico = service.cadastrar(dados);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dadosDetalhamentoTopico.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(sort = {"dataCriacao"}) Pageable pageable) {
        Page<DadosListagemTopico> page = service.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        DadosDetalhamentoTopico dadosDetalhamentoTopico = service.detalhar(id);
        return ResponseEntity.ok(dadosDetalhamentoTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        DadosDetalhamentoTopico dadosDetalhamentoTopico = service.atualizar(id, dados);
        return ResponseEntity.ok(dadosDetalhamentoTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
