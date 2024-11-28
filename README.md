
<h1>Sistema de Gerenciamento de Tarefas</h1>

<h2>Descrição</h2>
<p>Este é um sistema de gerenciamento de tarefas desenvolvido utilizando uma arquitetura de microsserviços. O projeto é dividido em dois serviços principais:</p>
<ul>
<li><strong>Usuários</strong>: Gerencia a autenticação, autorização e informações dos usuários.</li>
<li><strong>Tarefas</strong>: Responsável por criar, editar, listar e excluir tarefas.</li>
</ul>
<p>A interface frontend foi desenvolvida com <strong>Next.js</strong>, enquanto os microsserviços utilizam <strong>Java</strong> com o framework <strong>Spring Boot</strong>.</p>

<h2>Funcionalidades</h2>
<h3>Serviço de Usuários</h3>
<ul>
<li>Cadastro e gerenciamento de usuários.</li>
<li>Autenticação e geração de tokens JWT.</li>
<li>Autorização para acessar as funcionalidades do sistema.</li>
</ul>

<h3>Serviço de Tarefas</h3>
<ul>
<li>CRUD de tarefas (Criar, Ler, Atualizar, Excluir).</li>
<li>Validação de dados.</li>
<li>Gerenciamento de tarefas com prazos e status.</li>
</ul>

<h3>Frontend</h3>
<ul>
<li>Interface intuitiva para gerenciamento de tarefas.</li>
<li>Integração com os microsserviços via API.</li>
<li>Layout responsivo e estilizado com Tailwind CSS.</li>
</ul>

<h2>Tecnologias Utilizadas</h2>
<h3>Backend</h3>
<ul>
<li><strong>Java</strong> com <strong>Spring Boot</strong></li>
<li><strong>Spring Data JPA</strong>: Persistência e gerenciamento de dados.</li>
<li><strong>Spring Security</strong>: Autenticação e autorização.</li>
<li><strong>Spring Validation</strong>: Validação de dados de entrada.</li>
<li><strong>MySQL</strong>: Banco de dados relacional.</li>
<li><strong>JWT (JSON Web Tokens)</strong>: Gerenciamento de autenticação.</li>
<li><strong>Lombok</strong>: Redução de boilerplate no código.</li>
</ul>

<h3>Frontend</h3>
<ul>
<li><strong>Next.js</strong>: Framework React para SSR e SPA.</li>
<li><strong>React</strong>: Biblioteca para construção de interfaces.</li>
<li><strong>Tailwind CSS</strong>: Framework para estilização rápida e responsiva.</li>
<li><strong>JWT Decode</strong>: Biblioteca para decodificar tokens JWT.</li>
<li><strong>TypeScript</strong>: Superset do JavaScript para tipagem estática.</li>
</ul>

<h2>Instalação e Execução</h2>
<h3>Backend</h3>
<ol>
<li>Certifique-se de ter o <strong>Java 17+</strong> e o <strong>Maven</strong> instalados.</li>
<li>Configure o banco de dados MySQL:
    <ul>
    <li>Crie um banco de dados chamado <strong>task_management</strong>.</li>
    <li>Atualize as credenciais no arquivo <code>application.properties</code> do microsserviço.</li>
    </ul>
</li>
<li>No diretório de cada microsserviço (<code>users</code> e <code>tasks</code>), execute:
    <pre>
    mvn clean install
    mvn spring-boot:run
    </pre>
</li>
</ol>

<h3>Frontend</h3>
<ol>
<li>Certifique-se de ter o <strong>Node.js 18+</strong> instalado.</li>
<li>Instale as dependências:
    <pre>
    npm install
    </pre>
</li>
<li>Inicie o servidor de desenvolvimento:
    <pre>
    npm run dev
    </pre>
</li>
<li>Acesse a aplicação em <a href="http://localhost:3000" target="_blank">http://localhost:3000</a>.</li>
</ol>

<h2>Licença</h2>
<p>Este projeto é de código aberto e utiliza a licença <strong>MIT</strong>.</p>

