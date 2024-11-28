"use client"; // Indica que este componente é um componente de cliente

import { useState } from "react";
import { useRouter } from "next/navigation"; 

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null); 
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const userData = { email, password };

    try {
      const response = await fetch("http://localhost:8080/user/auth", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (!response.ok) {
        throw new Error("Erro ao fazer login");
      }

      const data = await response.json();
      
      console.log("Resposta da API:", data); 

      const token = data.access_token; 

      if (!token) {
        throw new Error("Token não retornado pela API.");
      }

      console.log("Token:", token); 

      localStorage.setItem("authToken", token);

      router.push("/dashboard");
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
        <h2 className="text-2xl font-bold mb-4 text-center">Fazer Login</h2>
        {error && <p className="text-red-500 mb-4">{error}</p>}
        <form onSubmit={handleSubmit} className="flex flex-col">
          <label htmlFor="email" className="mb-2">Email</label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="mb-4 p-2 border rounded"
          />
          <label htmlFor="password" className="mb-2">Senha</label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="mb-4 p-2 border rounded"
          />
          <button
            type="submit"
            className="h-10 px-4 text-sm font-medium text-white bg-blue-600 rounded-full hover:bg-blue-700 transition duration-200"
          >
             Fazer Login 
          </button>
        </form>
        <p className="mt-4 text-center">
          Não tem uma conta?{" "}
          <a href="/auth/signup" className="text-blue-600 hover:underline">
            Criar Conta
          </a>
        </p>
      </div>
    </div>
  );
}