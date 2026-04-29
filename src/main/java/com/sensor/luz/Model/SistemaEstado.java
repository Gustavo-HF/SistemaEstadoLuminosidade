package com.sensor.luz.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SistemaEstado {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    private String modo; 
    private int threshold;
    private boolean ledLigado;
    private int luminosidade;

    public SistemaEstado() {

    }

    public SistemaEstado(Long id, String modo, int threshold, boolean ledLigado, int luminosidade) {
        this.id = id;
        this.modo = modo;
        this.threshold = threshold;
        this.ledLigado = ledLigado;
        this.luminosidade = luminosidade;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public boolean isLedLigado() {
        return ledLigado;
    }

    public void setLedLigado(boolean ledLigado) {
        this.ledLigado = ledLigado;
    }

    public int getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    

    
}
