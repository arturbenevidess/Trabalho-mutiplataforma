package com.app.user_service.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("Usuário já existe");
  }
}
