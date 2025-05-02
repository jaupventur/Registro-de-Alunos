package aplicacao;

import gerenciamento.GerenciadorTurma;
import modelo.Aluno;
import java.util.Scanner;

public class AplicacaoGerenciamentoAlunos {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorTurma gerenciador;

    public static void main(String[] args) {
        System.out.println("=== Gerenciamento de Turma - Linguagem de Programação II ===");
        System.out.print("Informe a capacidade máxima da turma: ");
        int capacidadeMaxima = scanner.nextInt();
        scanner.nextLine();

        gerenciador = new GerenciadorTurma(capacidadeMaxima);

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluirAluno();
                    break;
                case 2:
                    alterarAluno();
                    break;
                case 3:
                    consultarPorNome();
                    break;
                case 4:
                    consultarPorMatricula();
                    break;
                case 5:
                    excluirAluno();
                    break;
                case 6:
                    gerenciador.imprimirListaOriginal();
                    break;
                case 7:
                    gerenciador.imprimirListaOrdenadaPorNome();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Incluir Aluno");
        System.out.println("2. Alterar Aluno");
        System.out.println("3. Consultar por Nome");
        System.out.println("4. Consultar por Matrícula");
        System.out.println("5. Excluir Aluno");
        System.out.println("6. Imprimir Lista Original");
        System.out.println("7. Imprimir Lista Ordenada por Nome");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }


    private static void incluirAluno() {
        if (gerenciador.getQuantidadeAtual() >= gerenciador.getCapacidadeMaxima()) {
            System.out.println("A turma já está com capacidade máxima!");
            return;
        }

        System.out.println("\n=== Incluir Aluno ===");

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();


        if (gerenciador.matriculaExiste(matricula)) {
            System.out.println("Erro: Matrícula já cadastrada!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        Aluno novoAluno = new Aluno(matricula, nome, curso);


        for (int i = 1; i <= 4; i++) {
            System.out.print("Nota da Prova " + i + ": ");
            double nota = scanner.nextDouble();
            scanner.nextLine();

            try {
                novoAluno.setNotaProva(i, nota);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                i--;
            }
        }

        if (gerenciador.incluirAluno(novoAluno)) {
            System.out.println("Aluno incluído com sucesso!");
        } else {
            System.out.println("Não foi possível incluir o aluno.");
        }
    }


    private static void alterarAluno() {
        System.out.println("\n=== Alterar Aluno ===");

        System.out.print("Informe a matrícula do aluno a ser alterado: ");
        String matricula = scanner.nextLine();

        Aluno aluno = gerenciador.consultarPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.println("Aluno encontrado: " + aluno);

        System.out.print("Novo nome (ou Enter para manter): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) {
            nome = aluno.getNome();
        }

        System.out.print("Novo curso (ou Enter para manter): ");
        String curso = scanner.nextLine();
        if (curso.isEmpty()) {
            curso = aluno.getCurso();
        }

        double[] novasNotas = new double[4];
        boolean alterarNotas = false;

        System.out.print("Deseja alterar as notas? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            alterarNotas = true;
            for (int i = 0; i < 4; i++) {
                System.out.print("Nova nota para a Prova " + (i + 1) + ": ");
                novasNotas[i] = scanner.nextDouble();
                scanner.nextLine();
            }
        }

        if (gerenciador.alterarAluno(matricula, nome, curso, alterarNotas ? novasNotas : null)) {
            System.out.println("Aluno alterado com sucesso!");
        } else {
            System.out.println("Não foi possível alterar o aluno.");
        }
    }


    private static void consultarPorNome() {
        System.out.println("\n=== Consultar por Nome ===");

        System.out.print("Informe o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Deseja apenas o primeiro registro? (S/N): ");
        String resposta = scanner.nextLine();
        boolean apenasOPrimeiro = resposta.equalsIgnoreCase("S");

        Aluno[] alunosEncontrados = gerenciador.consultarPorNome(nome, apenasOPrimeiro);

        if (alunosEncontrados.length == 0) {
            System.out.println("Nenhum aluno encontrado com este nome!");
        } else {
            System.out.println("Alunos encontrados:");
            for (int i = 0; i < alunosEncontrados.length; i++) {
                System.out.println((i + 1) + ". " + alunosEncontrados[i]);
            }
        }
    }


    private static void consultarPorMatricula() {
        System.out.println("\n=== Consultar por Matrícula ===");

        System.out.print("Informe a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = gerenciador.consultarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Nenhum aluno encontrado com esta matrícula!");
        } else {
            System.out.println("Aluno encontrado: " + aluno);
        }
    }


    private static void excluirAluno() {
        System.out.println("\n=== Excluir Aluno ===");

        System.out.print("Informe o nome do aluno a ser excluído: ");
        String nome = scanner.nextLine();

        if (gerenciador.excluirAluno(nome)) {
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Não foi possível excluir o aluno.");
        }
    }
}