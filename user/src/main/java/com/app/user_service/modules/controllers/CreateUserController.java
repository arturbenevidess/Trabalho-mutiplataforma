package com.app.user_service.modules.controllers;

import com.app.user_service.modules.entities.UserEntity;
import com.app.user_service.modules.repositories.UserRepository;
import com.app.user_service.modules.services.CreateUserService;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class CreateUserController {

  @Autowired
  private CreateUserService createUserService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity) {
    try {
      var result = this.createUserService.execute(userEntity);
      return ResponseEntity.ok().body(result);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable UUID id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build(); 
    }
  }
}
