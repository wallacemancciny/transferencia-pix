package com.sistemabancario.transferenciapix.controller;

import com.sistemabancario.transferenciapix.dto.UserDetailRequestTempDTO;
import com.sistemabancario.transferenciapix.dto.UserDetailResponseDTO;
import com.sistemabancario.transferenciapix.service.UserDetailService;
import org.mapstruct.Mapper;
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
    public ResponseEntity<UserDetailRequestTempDTO> create(@RequestBody UserDetailRequestTempDTO dto) {
        return ResponseEntity.ok(userDetailService.create(dto));
    }

}
