package com.br.app.tasks.modules.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

  private Long id;
  private String title;
  private String description;
  private LocalDateTime dueDate;
  private boolean completed;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
