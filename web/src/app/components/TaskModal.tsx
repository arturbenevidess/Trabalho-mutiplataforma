"use client";

import { useEffect, useState } from "react";

interface Task {
  id: string;
  title: string;
  description: string;
  dueDate: string;
  completed: boolean;
}

interface TaskModalProps {
  isOpen: boolean;
  onClose: () => void;
  fetchTasks: () => void;
  currentTask: Task | null;
  setCurrentTask: (task: Task | null) => void; 
}

export default function TaskModal({
  isOpen,
  onClose,
  fetchTasks,
  currentTask,
  setCurrentTask,
}: TaskModalProps) {
  const [title, setTitle] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [dueDate, setDueDate] = useState<string>("");

  useEffect(() => {
    if (isOpen) {
      if (currentTask) {
        setTitle(currentTask.title);
        setDescription(currentTask.description);
        setDueDate(currentTask.dueDate);
      } else {
        resetForm();
      }
    }
  }, [isOpen, currentTask]);

  const resetForm = () => {
    setTitle("");
    setDescription("");
    setDueDate("");
  };

  const handleClose = () => {
    resetForm(); 
    setCurrentTask(null); 
    onClose(); 
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const url = currentTask
      ? `http://localhost:8081/api/tasks/${currentTask.id}`
      : `http://localhost:8081/api/tasks`;

    const method = currentTask ? "PUT" : "POST";

    try {
      const taskPayload = {
        title: title.trim(),
        description: description.trim(),
        dueDate: dueDate.trim(),
        completed: currentTask?.completed || false,
      };

      const response = await fetch(url, {
        method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(taskPayload),
      });

      if (!response.ok) {
        throw new Error(
          `Erro ao ${method === "POST" ? "criar" : "atualizar"} tarefa`
        );
      }

      fetchTasks(); 
      handleClose(); 
    } catch (error) {
      console.error("Erro:", error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-1/2">
        <h2 className="text-xl font-bold mb-4">
          {currentTask ? "Editar Tarefa" : "Criar Tarefa"}
        </h2>
        <form onSubmit={handleSubmit}>
          <label className="block mb-2">Título</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
            className="border p-2 w-full mb-4"
          />

          <label className="block mb-2">Descrição</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
            className="border p-2 w-full mb-4"
          ></textarea>

          <label className="block mb-2">Data de Vencimento</label>
          <input
            type="datetime-local"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            required
            className="border p-2 w-full mb-4"
          />

          <div className="flex justify-between">
            <button
              type="submit"
              className="h-10 px-4 text-sm font-medium text-white bg-blue-600 rounded-full hover:bg-blue-700 transition duration-200"
            >
              {currentTask ? "Atualizar Tarefa" : "Criar Tarefa"}
            </button>
            <button
              type="button"
              onClick={handleClose}
              className="mt-2 h-10 px-4 text-sm font-medium text-red-500 border border-red-500 rounded-full hover:bg-red-500 hover:text-white transition duration-200"
            >
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
