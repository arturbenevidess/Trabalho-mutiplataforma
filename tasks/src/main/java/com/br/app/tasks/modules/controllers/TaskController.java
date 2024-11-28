package com.br.app.tasks.modules.controllers;

import com.br.app.tasks.exceptions.TaskNotFoundException;
import com.br.app.tasks.modules.dtos.TaskDTO;
import com.br.app.tasks.modules.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // Criar uma nova tarefa
  @PostMapping
  public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
    TaskDTO createdTask = taskService.createTask(taskDTO); 
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
  }

  // Listar todas as tarefas
  @GetMapping
  public ResponseEntity<List<TaskDTO>> getAllTasks() { 
    List<TaskDTO> tasks = taskService.getAllTasks(); 
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  // Obter uma tarefa por ID
  @GetMapping("/{id}")
  public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) { 
    TaskDTO task = taskService.getTaskById(id); 
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  // Atualizar uma tarefa
  @PutMapping("/{id}")
  public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
    TaskDTO updatedTask = taskService.updateTask(id, taskDTO); 
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

  // Deletar uma tarefa
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    if (taskService.deleteTask(id)) { 
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
    throw new TaskNotFoundException(id); // Lança exceção se a tarefa não for encontrada
  }
}