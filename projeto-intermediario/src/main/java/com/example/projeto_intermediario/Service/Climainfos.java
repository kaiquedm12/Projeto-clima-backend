package com.example.projeto_intermediario.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Climainfos {

    private final String CHAVE = "9d93dfe4d3c9bcc108aa7f7c7afc6cf3";
    private final String URL_API = "https://api.openweathermap.org/data/2.5/weather";
    private final RestTemplate restTemplate;

    public Climainfos() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> getClima(String cidade) {
        String url = URL_API + "?q=" + cidade + "&appid=" + CHAVE + "&lang=pt_br" + "&units=metric";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return ajeitarOsDados(response.getBody());
            } else {
                return MensagemErro("Não foi possível encontrar verifique a cidade informada.");
            }
        } catch (HttpClientErrorException e) {
            return MensagemErro("Erro na requisição: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            return MensagemErro("Erro de conexão: Não foi possível acessar o serviço de clima.");
        } catch (Exception e) {
            return MensagemErro("Erro inesperado: " + e.getMessage());
        }
    }

    private Map<String, Object> ajeitarOsDados(Map<String, Object> dados) {
        Map<String, Object> dadosAjeitados = new HashMap<>();

        Map<String, Object> main = (Map<String, Object>) dados.get("main");
        Map<String, Object> wind = (Map<String, Object>) dados.get("wind");
        Map<String, Object> weather = ((List<Map<String, Object>>) dados.get("weather")).get(0);

        dadosAjeitados.put("cidade", dados.get("name"));
        dadosAjeitados.put("temperatura", main.get("temp") + " ºC");
        dadosAjeitados.put("sensacao termica", main.get("feels_like") + " ºC");
        dadosAjeitados.put("temperatura minima", main.get("temp_min") + " ºC");
        dadosAjeitados.put("temperatura maxima", main.get("temp_max") + " ºC");
        dadosAjeitados.put("umidade", main.get("humidity") + "%");
        dadosAjeitados.put("vento", wind.get("speed") + " m/s");
        dadosAjeitados.put("descricao", weather.get("description"));

        return dadosAjeitados;
    }

    public Map<String, Object> processDados(Map<String, Object> requestData) {
        Optional.ofNullable(requestData)
                .orElseThrow(() -> new IllegalArgumentException("Os dados de entrada não pode ser vazio."));

        requestData.put("status", "Dados recebidos com sucesso!");
        return requestData;
    }

    public Map<String, String> getSobre() {
        Map<String, String> sobreInfo = new HashMap<>();
        sobreInfo.put("estudante", "Kaique Demetrio");
        sobreInfo.put("projeto", "Serviço de clima");
        return sobreInfo;
    }

    private Map<String, Object> MensagemErro(String mensagem) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("erro", mensagem);
        return erro;
    }
}
