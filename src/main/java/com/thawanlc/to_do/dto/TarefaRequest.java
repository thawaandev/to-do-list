package com.thawanlc.to_do.dto;

import com.thawanlc.to_do.entity.enums.TipoPrioridade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TarefaRequest(
    @NotBlank @Size(max = 30)String descricao,
    @NotNull TipoPrioridade tipoPrioridade
) {
    
}
