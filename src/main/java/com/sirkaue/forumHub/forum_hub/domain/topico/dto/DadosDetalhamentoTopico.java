package com.sirkaue.forumHub.forum_hub.domain.topico.dto;

import com.sirkaue.forumHub.forum_hub.domain.topico.Status;
import com.sirkaue.forumHub.forum_hub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String nomeAutor, Status status) {
    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getDataCriacao(),topico.getAutor(),topico.getStatus());
    }
}
