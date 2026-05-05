package com.thawanlc.to_do.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thawanlc.to_do.dto.TarefaRequest;
import com.thawanlc.to_do.dto.TarefaResponse;
import com.thawanlc.to_do.entity.Tarefa;
import com.thawanlc.to_do.entity.enums.TipoPrioridade;
import com.thawanlc.to_do.entity.enums.TipoStatus;
import com.thawanlc.to_do.exceptions.RecursoNaoEncontrado;
import com.thawanlc.to_do.mapper.TarefaMapper;
import com.thawanlc.to_do.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaResponse criarTarefa(TarefaRequest request) {
        Tarefa tarefa = TarefaMapper.toEntity(request); 
        tarefaRepository.saveAndFlush(tarefa);
        return TarefaMapper.toResponse(tarefa);
    }

    public TarefaResponse cancelarTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Tarefa " + id + " não encontrado"));

        switch (tarefa.getTipoStatus()) {
            case CONCLUIDO:
                new RecursoNaoEncontrado("Tarefa Concluida não pode ser cancelada...");
                break;
            case CANCELADO:
                new RecursoNaoEncontrado("Tarefa já está cancelada...");
            default:
                tarefa.setTipoStatus(TipoStatus.CANCELADO);
                tarefa.cancelar();
                tarefaRepository.saveAndFlush(tarefa);
                break;
        }            
        return TarefaMapper.toResponse(tarefa);
        
    }

    public TarefaResponse concluirTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Tarefa " + id + " não encontrado"));

        switch (tarefa.getTipoStatus()) {
            case CONCLUIDO:
                new RuntimeException("Tarefa já concluida");
                break; 
            case CANCELADO:
                new RuntimeException("Tarefa Cancelada no Banco de Dados...");
                break;
            default:
                tarefa.setTipoStatus(TipoStatus.CONCLUIDO);
                tarefaRepository.saveAndFlush(tarefa);
                break;
        }
        return TarefaMapper.toResponse(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public void verificarTarefasExpiradas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();

        for(Tarefa t : tarefas) {
            t.verificarSeExpirou();
        }
        tarefaRepository.saveAll(tarefas);
    }

    public List<Tarefa> filtrarPorPrioridade(TipoPrioridade tipo) {
        return tarefaRepository.findByTipoPrioridade(tipo);
    }

    public List<Tarefa> filtrarPorStatus(TipoStatus status) {
        return tarefaRepository.findByTipoStatus(status);
    }

}
