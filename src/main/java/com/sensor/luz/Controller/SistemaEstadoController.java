package com.sensor.luz.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.luz.Service.SistemaEstadoService;

@RestController
@RequestMapping("/")

public class SistemaEstadoController {

    @Autowired
    private SistemaEstadoService service;

    private int ultimaLuminosidade;

    @PostMapping("/sensor/luminosidade")
    public String receberLuminosidade(@RequestBody Map<String, Integer> body) {
        int valor = body.get("valor");
        ultimaLuminosidade = valor;

        return service.processarLuminosidade(valor);
    }

}
