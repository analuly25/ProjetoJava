# Trabalho - Linguagem e Técnicas de Programação II

Ana Luiza Gomes Santana - 22409471

Bárbara Parente de Carvalho Soares - 22402134


-----------------------------------------------------------------------------------------------------------------------------------

# Sistema de Agência de Viagens 

## 📄 Descrição do Sistema

Este sistema foi desenvolvido com o objetivo de gerenciar o cadastro de **clientes**, **pacotes de viagens** e **serviços adicionais** para uma agência de turismo.  

A aplicação permite:
- O cadastro de **clientes nacionais** (com CPF) e **clientes estrangeiros** (com passaporte), garantindo a validação dos documentos conforme a nacionalidade.
- O registro de **pacotes de viagem** com destino, descrição, duração, preço e tipo (ex: nacional, internacional).
- A associação entre clientes e pacotes, permitindo que um **cliente contrate mais de um pacote** de viagem, por meio de **pedidos** registrados no sistema.
- A inclusão de **serviços adicionais** a cada pedido, como translado, passeios turísticos, aluguel de veículos, entre outros, com controle individual de valor e descrição.

### 🔍 Funcionalidades implementadas:
- Cadastro de clientes (nacionais e estrangeiros)
- Cadastro de pacotes de viagem
- Registro de pedidos (cliente + pacote)
- Inclusão de serviços adicionais em pedidos
- Relatórios:
  - Pacotes contratados por cliente
  - Clientes que contrataram determinado pacote

### 🛠️ Tecnologias utilizadas:
- Linguagem: Java
- Banco de Dados: MySQL
- Conexão: JDBC
- Organização do código: DAO, Model, View (menus via terminal)


