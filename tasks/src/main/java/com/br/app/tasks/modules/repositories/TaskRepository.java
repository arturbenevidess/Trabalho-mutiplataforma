package com.br.app.tasks.modules.repositories;

import com.br.app.tasks.modules.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

  // Método para encontrar tarefas por status de conclusão
  List<TaskEntity> findByCompleted(boolean completed);

  // Método para encontrar tarefas por título
  List<TaskEntity> findByTitleContainingIgnoreCase(String title);

  // Método para encontrar tarefas por data de vencimento
  List<TaskEntity> findByDueDateBefore(LocalDateTime dueDate);

}