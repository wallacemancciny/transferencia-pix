package com.sistemabancario.transferenciapix.controller;

import com.sistemabancario.transferenciapix.dto.DashboardDTO;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final WebClient webClient;

    public DashboardController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/totals")
    public ResponseEntity<DashboardDTO> getDashboardTotals() {

    String token = webClient.post()
            .uri("http://localhost:8081/auth/login")
            .bodyValue(Map.of(
                    "login", "user-api-wallace3",
                    "password", "123123123"
            ))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        String tokenClear = token.replace("{\"token\":\"", "").replace("\"}", "");


        Map response = webClient.get()
                .uri("http://localhost:8081/emprestimos/")
                .header("Authorization", "Bearer " + tokenClear)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Extrair a lista real de dentro do "data"
        List data = (List) response.get("data");

        int total = data.size();

        System.out.println("Total emprestimos: " + total);

        DashboardDTO dashboard = new DashboardDTO(
                0L,
                (long) total,
                0L,
                0L,
                0L,
                0L
        );
        return ResponseEntity.ok(dashboard);
    }
}
