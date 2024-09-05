# Projeto CRUD Java com SQLite

Este projeto demonstra a manipulação de dados em um banco de dados SQLite utilizando Java. O código inclui operações básicas de CRUD (Criar, Ler, Atualizar e Deletar) e é implementado usando JDBC para a interação com o banco de dados.

## Funcionalidades

- Adicionar dados ao banco de dados
- Remover dados do banco de dados
- Atualizar dados no banco de dados
- Buscar dados no banco de dados
- Inserir múltiplos registros em uma única operação
- Buscar registros usando um padrão (LIKE)

## Tecnologias Utilizadas

- **Java**: Linguagem de programação usada para desenvolver a aplicação.
- **SQLite**: Sistema de gerenciamento de banco de dados usado para armazenar os dados.
- **JDBC**: API Java para conectar e executar comandos SQL em um banco de dados.

1. Compilar o Código
javac -cp "lib/sqlite-jdbc-<version>.jar" src/main/java/com/crud/DatabaseManager.java

2. Executar o Código
java -cp "lib/sqlite-jdbc-<version>.jar;src/main/java" com.crud.DatabaseManager

