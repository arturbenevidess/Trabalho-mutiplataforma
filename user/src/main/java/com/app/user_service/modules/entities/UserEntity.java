package com.app.user_service.modules.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "O nome não pode estar em branco")
  private String name;

  @Email(message = "O campo email deve conter um email válido")
  private String email;

  @Length(min = 5, max = 100, message = "A senha deve conter entre 5 a 10 caracteres")
  @NotBlank(message = "A senha não pode estar em branco")
  private String password;

  @CreationTimestamp
  private LocalDateTime createAt;

}
