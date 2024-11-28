package com.app.user_service.modules.controllers;

import com.app.user_service.modules.dtos.AuthUserRequestDTO;
import com.app.user_service.modules.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class AuthUserController {

  @Autowired
  private AuthUserService authUserService;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO) {
    try {
      var token = this.authUserService.execute(authUserRequestDTO);
      return ResponseEntity.ok().body(token);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
