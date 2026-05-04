package com.sensor.luz.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensor.luz.Model.SistemaEstado;
import com.sensor.luz.Service.SistemaEstadoService;



@Controller
public class SistemaEstadoController {

    @Autowired
    private SistemaEstadoService service;

    // Página principal
    @GetMapping("/")
    public String index(Model model) {
        SistemaEstado estado = service.getEstadoAtual();
        model.addAttribute("estado", estado);
        return "index";
    }

    // Recebe luminosidade (ESP32 ainda usa JSON)
    @PostMapping("/sensor/luminosidade")
    @ResponseBody
    public String receberLuminosidade(@RequestBody Map<String, Integer> body) {
        int valor = body.get("valor");
        return service.processarLuminosidade(valor);
    }

    // Alterar modo (form HTML)
    @PostMapping("/modo")
    public String mudarModo(@RequestParam String modo) {
        service.setModo(modo);
        return "redirect:/";
    }

    // Controlar LED (form HTML)
    @PostMapping("/led")
    public String controlarLed(@RequestParam boolean estado) {
        service.setLed(estado);
        return "redirect:/";
    }

    // Alterar threshold (form HTML)
    @PostMapping("/config/threshold")
    public String setThreshold(@RequestParam int valor) {
        service.setThreshold(valor);
        return "redirect:/";
    }
}
