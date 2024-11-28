package com.app.user_service.modules.services;

import com.app.user_service.exceptions.UserFoundException;
import com.app.user_service.modules.entities.UserEntity;
import com.app.user_service.modules.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserEntity execute(UserEntity userEntity) {

    // Verifica se o email já está em uso
    this.userRepository
            .findByEmail(userEntity.getEmail())
            .ifPresent(user -> {
              throw new UserFoundException();
            });

    // Codifica a senha do usuário antes de salvar
    var password = passwordEncoder.encode(userEntity.getPassword());
    userEntity.setPassword(password);

    return this.userRepository.save(userEntity);
  }
}
