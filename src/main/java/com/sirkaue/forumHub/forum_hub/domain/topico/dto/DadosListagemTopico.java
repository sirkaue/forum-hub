package com.sirkaue.forumHub.forum_hub.domain.topico.dto;

import com.sirkaue.forumHub.forum_hub.domain.topico.Topico;
import com.sirkaue.forumHub.forum_hub.domain.topico.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        String autor,
        String curso) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
    }
}
