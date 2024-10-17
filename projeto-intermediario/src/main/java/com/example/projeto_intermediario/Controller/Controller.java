package com.example.projeto_intermediario.Controller;

import com.example.projeto_intermediario.Service.Climainfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private Climainfos climainfos;

    @GetMapping("/api/clima")
    public Map<String, Object> getTempo(@RequestParam String cidade) {
        return climainfos.getClima(cidade);
    }

    @PostMapping("/api/processo")
    public Map<String, Object> processDados(@RequestBody Map<String, Object> requestData) {
        return climainfos.processDados(requestData);
    }

    @GetMapping("/sobre")
    public Map<String, String> sobre() {
        return climainfos.getSobre();
    }
}
