package com.sistemabancario.transferenciapix.controller;

import com.sistemabancario.transferenciapix.dto.DashboardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/totals")
    public ResponseEntity<DashboardDTO> getDashboardTotals() {
        DashboardDTO dashboard = new DashboardDTO(100L,
                50000L,
                45000L,
                5000L,
                2000L,
                150L);
        return ResponseEntity.ok(dashboard);
    }
}
