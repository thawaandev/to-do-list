package com.thawanlc.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TarefaScheduler {
    
    @Autowired private TarefaService tarefaService;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 12)
    public void executarVerificacao() {
        tarefaService.verificarTarefasExpiradas();
        System.out.println("Verificando Tarefas...");
    }

}
