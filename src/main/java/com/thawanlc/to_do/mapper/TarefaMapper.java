package com.thawanlc.to_do.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.thawanlc.to_do.dto.TarefaRequest;
import com.thawanlc.to_do.dto.TarefaResponse;
import com.thawanlc.to_do.entity.Tarefa;
import com.thawanlc.to_do.entity.enums.TipoStatus;

@Component
public class TarefaMapper {
    

    public static Tarefa toEntity(TarefaRequest request) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(request.descricao());
        tarefa.setTipoStatus(TipoStatus.PENDENTE);
        tarefa.setTipoPrioridade(request.tipoPrioridade());
        tarefa.setCriadoEm(LocalDateTime.now());
        tarefa.setExpiraEm(LocalDateTime.now().plusDays(1));
        return tarefa;
    }

    public static TarefaResponse toResponse(Tarefa t) {
        return new TarefaResponse(
            t.getId(),
            t.getDescricao(),
            t.getTipoStatus(),
            t.getTipoPrioridade(),
            t.getCriadoEm(),
            t.getExpiraEm()
        );
    }


}
