package com.sirkaue.forumHub.forum_hub.service;

import com.sirkaue.forumHub.forum_hub.domain.topico.Topico;
import com.sirkaue.forumHub.forum_hub.domain.topico.TopicoRepository;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosCadastroTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosDetalhamentoTopico;
import com.sirkaue.forumHub.forum_hub.domain.topico.dto.DadosListagemTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {
        Topico topico = repository.save(new Topico(dados));
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemTopico> listar(Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemTopico::new);
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoTopico detalhar(Long id) {
        Topico topico = repository.getReferenceById(id);
        return new DadosDetalhamentoTopico(topico);
    }
}
