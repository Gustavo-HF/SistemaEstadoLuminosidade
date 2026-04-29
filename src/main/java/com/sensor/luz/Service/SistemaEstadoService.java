package com.sensor.luz.Service;

import org.springframework.stereotype.Service;

@Service
public class SistemaEstadoService {

    private String modo = "AUTOMATICO";
    private int threshold = 500;
    private boolean ledLigado = false;

    public String processarLuminosidade(int valor) {

        if (modo.equals("AUTOMATICO")) {
            if (valor < threshold) {
                ledLigado = true;
            } else {
                ledLigado = false;
            }
        }

        return ledLigado ? "ON" : "OFF";
    }
}
