package com.thawanlc.to_do.dto;


import java.time.LocalDateTime;

import com.thawanlc.to_do.entity.enums.TipoPrioridade;
import com.thawanlc.to_do.entity.enums.TipoStatus;

public record TarefaResponse(
    Long id,
    String descricao,
    TipoStatus tipoStatus,
    TipoPrioridade tipoPrioridade,
    LocalDateTime criadoEm,
    LocalDateTime expiraEm
) {
    
}
