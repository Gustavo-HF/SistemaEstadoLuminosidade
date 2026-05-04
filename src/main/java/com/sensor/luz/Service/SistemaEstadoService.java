package com.sensor.luz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.luz.Model.SistemaEstado;
import com.sensor.luz.Repository.SistemaEstadoRepository;

@Service
public class SistemaEstadoService {

    @Autowired
    private SistemaEstadoRepository repository;

    private SistemaEstado getEstado() {
        return repository.findAll().stream()
                .findFirst()
                .orElseGet(() -> {
                    SistemaEstado novo = new SistemaEstado();
                    novo.setModo("AUTOMATICO");
                    novo.setThreshold(500);
                    novo.setLedLigado(false);
                    return repository.save(novo);
                });
    }

    public String processarLuminosidade(int valor) {
        SistemaEstado estado = getEstado();

        estado.setLuminosidade(valor);

        if (estado.getModo().equals("AUTOMATICO")) {
            estado.setLedLigado(valor < estado.getThreshold());
        }

        repository.save(estado);

        return estado.isLedLigado() ? "ON" : "OFF";
    }

    public void setModo(String novoModo) {
        SistemaEstado estado = getEstado();
        estado.setModo(novoModo);
        repository.save(estado);
    }

    public void setLed(boolean led) {
        SistemaEstado estado = getEstado();

        if (estado.getModo().equals("MANUAL")) {
            estado.setLedLigado(led);
            repository.save(estado);
        }
    }

    public void setThreshold(int threshold) {
        SistemaEstado estado = getEstado();
        estado.setThreshold(threshold);
        repository.save(estado);
    }

    public SistemaEstado getEstadoAtual() {
        return getEstado();
    }

}
