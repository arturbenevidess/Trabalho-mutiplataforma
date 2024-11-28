"use client"; 

import { useState } from "react";
import { useRouter } from "next/navigation"; 

export default function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null); 
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const userData = { name, email, password };

    try {
      const response = await fetch("http://localhost:8080/user/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (!response.ok) {
        throw new Error("Erro ao criar usuário");
      }

      router.push("/auth/login");
    } catch (error: unknown) {
      if (error instanceof Error) {
        setError(error.message); 
      } else {
        setError("Ocorreu um erro inesperado."); 
      }
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen p-8 bg-background text-foreground">
      <div className="w-full max-w-md p-6 bg-gray-100 rounded-lg shadow-md">
        <h2 className="text-2xl font-bold mb-4 text-center">Criar Conta</h2>
        {error && <p className="text-red-500 mb-4">{error}</p>}
        <form onSubmit={handleSubmit} className="flex flex-col">
          <label htmlFor="name" className="mb-2">
            Nome
          </label>
          <input
            id="name"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
            className="mb-4 p-2 border border-slate-700 rounded"
          />
          <label htmlFor="email" className="mb-2">
            Email
          </label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="mb-4 p-2 border border-slate-700 rounded"
          />
          <label htmlFor="password" className="mb-2">
            Senha
          </label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="mb-4 p-2 border border-slate-700 rounded"
          />
          <button
            type="submit"
            className="h-10 px-4 text-sm font-medium text-white bg-blue-600 rounded-full hover:bg-blue-700 transition duration-200"
          >
            Criar Conta
          </button>
        </form>
        <p className="mt-4 text-center">
          Já tem uma conta?{" "}
          <a href="/auth/login" className="text-blue-600 hover:underline">
            Fazer Login
          </a>
        </p>
      </div>
    </div>
  );
}
