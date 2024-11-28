"use client";

import React from "react";

interface Task {
  id: string;
  title: string;
  description: string;
  dueDate: string;
  completed: boolean;
}

interface TaskCardProps {
  task: Task;
  onEdit: () => void;
  onDelete: () => void;
}

export default function TaskCard({ task, onEdit, onDelete }: TaskCardProps) {
  const handleDelete = async () => {
    try {
      const response = await fetch(`http://localhost:8081/api/tasks/${task.id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Erro ao deletar a tarefa");
      }

      alert("Tarefa deletada com sucesso!");
      onDelete();
    } catch (error) {
      console.error("Erro ao deletar tarefa:", error);
    }
  };

  return (
    <div className="border rounded-lg p-4 shadow-md">
      <h3 className="font-bold">{task.title}</h3>
      <p>{task.description}</p>
      <p>Data de Vencimento: {new Date(task.dueDate).toLocaleDateString()}</p>
      <div className="flex justify-between mt-4">
        <button onClick={onEdit} className="text-blue-600 hover:text-blue-800">
          Editar
        </button>
        <button
          onClick={handleDelete}
          className="text-red-600 hover:text-red-800"
        >
          Deletar
        </button>
      </div>
    </div>
  );
}
