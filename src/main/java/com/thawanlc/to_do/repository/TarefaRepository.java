package com.thawanlc.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thawanlc.to_do.entity.Tarefa;
import java.util.List;
import com.thawanlc.to_do.entity.enums.TipoPrioridade;
import com.thawanlc.to_do.entity.enums.TipoStatus;



public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByTipoPrioridade(TipoPrioridade tipoPrioridade);
    List<Tarefa> findByTipoStatus(TipoStatus tipoStatus);
}
