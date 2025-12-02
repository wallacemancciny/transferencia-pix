package com.sistemabancario.transferenciapix.controller;
import com.sistemabancario.transferenciapix.dto.UserDetailRequestDTO;
import com.sistemabancario.transferenciapix.dto.UserDetailResponseDTO;
import com.sistemabancario.transferenciapix.service.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
