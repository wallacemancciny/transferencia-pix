package com.sistemabancario.transferenciapix.controller;
import com.sistemabancario.transferenciapix.dto.UserDetailRequestDTO;
import com.sistemabancario.transferenciapix.dto.UserDetailResponseDTO;
import com.sistemabancario.transferenciapix.dto.UserResponseDTO;
import com.sistemabancario.transferenciapix.service.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-detail")
public class UserDetailController {

    private final UserDetailService userDetailService;

    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping
    public ResponseEntity<UserDetailResponseDTO> create(@RequestBody UserDetailRequestDTO dto) {
        return ResponseEntity.ok(userDetailService.create(dto));
    }

    @GetMapping("/{city}")
    public ResponseEntity<List<UserResponseDTO>> getUserByCity(@PathVariable String city) {
        return ResponseEntity.ok(userDetailService.getUserByCity(city));
    }

}
