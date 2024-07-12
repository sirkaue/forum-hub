package com.sirkaue.forumHub.forum_hub.controller;

import com.sirkaue.forumHub.forum_hub.domain.topico.Topico;
import com.sirkaue.forumHub.forum_hub.domain.topico.TopicoRepository;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosListagemTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {

        Topico topicos = repository.save(new Topico(dados));
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topicos));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(sort = {"dataCriacao"}) Pageable pageable) {
        Page<DadosListagemTopico> page = repository.findAll(pageable).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }


}
