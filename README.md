# Sistema de Gerenciamento de Alunos

## Descrição
Este projeto implementa um sistema de gerenciamento para armazenar e manipular dados de alunos da turma de Linguagem de Programação II. O sistema permite registrar informações como matrícula, nome, curso e notas de provas, além de realizar diversas operações de consulta e manipulação desses dados.

## Funcionalidades

- **Inclusão de Alunos**: Adicionar alunos à turma até o limite máximo definido na criação da turma
- **Alteração de Dados**: Modificar informações de alunos já registrados
- **Consultas**:
  - Por nome (retornando o primeiro registro ou todos)
  - Por matrícula
- **Exclusão**: Remover alunos da lista com base no nome
- **Listagem**:
  - Na ordem de inserção
  - Ordenada por nome
- **Validação**: Verificar se um aluno com determinada matrícula já existe no sistema

## Como Usar

1. Clone o repositório
2. Abra o projeto em seu IDE Java preferido
3. Execute a classe principal no pacote `aplicacao` para iniciar o sistema

## Regras de Negócio

- Matrículas são únicas no sistema
- Cada aluno possui 4 provas com nota e peso
- A operação de inclusão retorna:
  - `true`: Se o aluno foi adicionado com sucesso
  - `false`: Se já existe um aluno com a mesma matrícula

## Requisitos

- JDK 8 ou superior
- IDE compatível com Java (IntelliJ IDEA, Eclipse, NetBeans, etc.)

## Autor

[João Pedro Ventura]
