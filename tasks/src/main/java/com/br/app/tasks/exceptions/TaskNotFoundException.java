package com.br.app.tasks.exceptions;


public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(Long id) {
    super("Tarefa não encontrada com ID: " + id);
  }
}