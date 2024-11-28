import Image from "next/image";
import { LogIn, UserPlus } from "lucide-react"; 

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-8 bg-background text-foreground">
      <h1 className="text-4xl font-bold mb-4">Gerenciador de Tarefas</h1>
      <p className="text-lg text-center mb-8 max-w-lg">
        Organize suas tarefas de forma simples e eficiente. Com nosso gerenciador, você pode adicionar, editar e acompanhar suas atividades diárias, garantindo que nada fique esquecido!
      </p>
      <Image
        src="https://img.freepik.com/vetores-gratis/ilustracao-do-conceito-de-tarefa_114360-22492.jpg?t=st=1732721904~exp=1732725504~hmac=b8f9f9c818d687d3511970032f3e3db16cd27d15c8d0e3723faeba6559f6e871&w=740"
        alt="Ilustração de tarefas"
        width={350} 
        height={60} 
        className="mb-8 rounded-lg"
      />
      <div className="flex gap-4">
        <a
          href="/auth/login"
          className="flex items-center justify-center h-10 px-4 text-sm font-medium text-white bg-blue-600 rounded-full hover:bg-blue-700 transition duration-200"
        >
          <LogIn className="mr-2" /> Fazer Login
        </a>
        <a
          href="/auth/signup"
          className="flex items-center justify-center h-10 px-4 text-sm font-medium text-white bg-zinc-800 rounded-full hover:bg-gray-700 transition duration-200"
        >
          <UserPlus className="mr-2" /> Criar Conta
        </a>
      </div>
    </div>
  );
}