package com.sirkaue.forumHub.forum_hub.domain.topico.dto;

import com.sirkaue.forumHub.forum_hub.domain.topico.Topico;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String curso,
        String mensagem,
        String autor) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getCurso(),
                topico.getMensagem(),
                topico.getAutor());
    }
}
