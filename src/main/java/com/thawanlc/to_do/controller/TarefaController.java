package com.thawanlc.to_do.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thawanlc.to_do.dto.TarefaRequest;
import com.thawanlc.to_do.dto.TarefaResponse;
import com.thawanlc.to_do.entity.Tarefa;
import com.thawanlc.to_do.entity.enums.TipoPrioridade;
import com.thawanlc.to_do.entity.enums.TipoStatus;
import com.thawanlc.to_do.service.TarefaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired private TarefaService tarefaService;
    
    @PostMapping
    public ResponseEntity<TarefaResponse> salvarTarefa(@RequestBody TarefaRequest request) {
        tarefaService.criarTarefa(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<TarefaResponse> cancelarTarefa(@PathVariable Long id) {
        tarefaService.cancelarTarefa(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/concluir/{id}")
    public ResponseEntity<TarefaResponse> concluirTarefa(@PathVariable Long id) {
        tarefaService.concluirTarefa(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Tarefa> buscarTarefas() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/prioridade")
    public ResponseEntity<List<Tarefa>> buscarPorPrioridade(@RequestParam TipoPrioridade tipo) {
        return ResponseEntity.ok(tarefaService.filtrarPorPrioridade(tipo));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Tarefa>> buscarPorStatus(@RequestParam TipoStatus status) {
        return ResponseEntity.ok(tarefaService.filtrarPorStatus(status));
    }
    
    
    
    

}
