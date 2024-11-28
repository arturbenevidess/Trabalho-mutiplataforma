package com.br.app.tasks.modules.services;

import com.br.app.tasks.exceptions.TaskNotFoundException;
import com.br.app.tasks.modules.dtos.TaskDTO;
import com.br.app.tasks.modules.entities.TaskEntity;
import com.br.app.tasks.modules.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository; 
  }

  // Criar uma nova tarefa
  public TaskDTO createTask(TaskDTO taskDTO) {
    TaskEntity taskEntity = new TaskEntity();
    taskEntity.setTitle(taskDTO.getTitle());
    taskEntity.setDescription(taskDTO.getDescription());
    taskEntity.setDueDate(taskDTO.getDueDate());
    taskEntity.setCompleted(false); 
    taskEntity.setCreatedAt(LocalDateTime.now());
    taskEntity.setUpdatedAt(LocalDateTime.now());

    TaskEntity savedTask = taskRepository.save(taskEntity);
    return mapToDTO(savedTask);
  }

  // Listar todas as tarefas
  public List<TaskDTO> getAllTasks() {
    List<TaskEntity> tasks = taskRepository.findAll(); // Obter todas as tarefas
    return tasks.stream().map(this::mapToDTO).toList();
  }

  // Obter uma tarefa por ID
  public TaskDTO getTaskById(Long id) {
    return taskRepository.findById(id)
        .map(this::mapToDTO)
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  // Atualizar uma tarefa
  public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
    return taskRepository.findById(id).map(existingTask -> {
      existingTask.setTitle(taskDTO.getTitle());
      existingTask.setDescription(taskDTO.getDescription());
      existingTask.setDueDate(taskDTO.getDueDate());
      existingTask.setCompleted(taskDTO.isCompleted());
      existingTask.setUpdatedAt(LocalDateTime.now());

      TaskEntity updatedTask = taskRepository.save(existingTask);
      return mapToDTO(updatedTask);
    }).orElseThrow(() -> new TaskNotFoundException(id));
  }

  // Deletar uma tarefa
  public boolean deleteTask(Long id) {
    if (taskRepository.existsById(id)) { // Verificando se a tarefa existe
      taskRepository.deleteById(id);
      return true; 
    }
    return false; 
  }

  // Método auxiliar para mapear TaskEntity para TaskDTO
  private TaskDTO mapToDTO(TaskEntity taskEntity) {
    return new TaskDTO(
        taskEntity.getId(),
        taskEntity.getTitle(),
        taskEntity.getDescription(),
        taskEntity.getDueDate(),
        taskEntity.isCompleted(),
        taskEntity.getCreatedAt(),
        taskEntity.getUpdatedAt());
  }
}