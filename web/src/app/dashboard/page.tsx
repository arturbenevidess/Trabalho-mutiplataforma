"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import TaskModal from "@/app/components/TaskModal";
import TaskCard from "@/app/components/TaskCard";

interface Task {
  id: string;
  title: string;
  description: string;
  dueDate: string;
  completed: boolean;
}

export default function Dashboard() {
  const router = useRouter();
  const [tasks, setTasks] = useState<Task[]>([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [currentTask, setCurrentTask] = useState<Task | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    if (!token) {
      router.push("/auth/login");
    } else {
      fetchTasks();
    }
  }, [router]);

  const fetchTasks = async () => {
    const token = localStorage.getItem("authToken");

    try {
      const response = await fetch(`http://localhost:8081/api/tasks`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        const data: Task[] = await response.json();
        setTasks(data);
      } else {
        console.error("Failed to fetch tasks", response.status);
      }
    } catch (error) {
      console.error("Erro ao buscar tarefas:", error);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-8 bg-background text-foreground">
      <h1 className="text-3xl font-bold mb-4">Bem-vindo ao seu Dashboard!</h1>
      <p className="mb-4">Aqui você pode gerenciar suas tarefas facilmente.</p>
      <button
        onClick={() => {
          setCurrentTask(null); 
          setModalOpen(true);
        }}
        className="mb-6 h-10 px-4 text-sm font-medium text-white bg-blue-600 rounded-full hover:bg-blue-700 transition duration-200"
      >
        Criar Tarefa
      </button>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-6 mt-20">
        {tasks.map((task) => (
          <TaskCard
            key={task.id}
            task={task}
            onEdit={() => {
              setCurrentTask(task);
              setModalOpen(true);
            }}
            onDelete={fetchTasks}
          />
        ))}
      </div>
      <TaskModal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        fetchTasks={fetchTasks}
        currentTask={currentTask}
        setCurrentTask={setCurrentTask} 
      />
    </div>
  );
}
