package com.thawanlc.to_do.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thawanlc.to_do.entity.enums.TipoPrioridade;
import com.thawanlc.to_do.entity.enums.TipoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tarefas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoStatus tipoStatus;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrioridade tipoPrioridade;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime expiraEm;
    private boolean cancelado;

    public void verificarSeExpirou() {
        if(LocalDateTime.now().isAfter(expiraEm)) {
            setTipoStatus(TipoStatus.CANCELADO);
            cancelar();
            System.out.println("Tarefa Cancelada: " + descricao);
        }
    }

    public void cancelar() {
        setCancelado(true);
    }


    
}
