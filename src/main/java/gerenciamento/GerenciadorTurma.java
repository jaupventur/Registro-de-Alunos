package gerenciamento;

import modelo.Aluno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GerenciadorTurma {
    private Aluno[] alunos;
    private int quantidadeAtual;
    private int capacidadeMaxima;


    public GerenciadorTurma(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunos = new Aluno[capacidadeMaxima];
        this.quantidadeAtual = 0;
    }


    public boolean incluirAluno(Aluno aluno) {

        if (quantidadeAtual >= capacidadeMaxima) {
            System.out.println("A turma está com capacidade máxima atingida.");
            return false;
        }


        if (consultarPorMatricula(aluno.getMatricula()) != null) {
            System.out.println("Já existe um aluno com a matrícula " + aluno.getMatricula());
            return false;
        }

        alunos[quantidadeAtual] = aluno;
        quantidadeAtual++;
        return true;
    }


    public boolean alterarAluno(String matricula, String novoNome, String novoCurso, double[] novasNotas) {
        Aluno aluno = consultarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno com matrícula " + matricula + " não encontrado.");
            return false;
        }


        aluno.setNome(novoNome);
        aluno.setCurso(novoCurso);


        if (novasNotas != null && novasNotas.length > 0) {
            for (int i = 0; i < Math.min(4, novasNotas.length); i++) {
                aluno.setNotaProva(i + 1, novasNotas[i]);
            }
        }

        return true;
    }


    public Aluno[] consultarPorNome(String nome, boolean apenasOPrimeiro) {
        List<Aluno> alunosEncontrados = new ArrayList<>();

        for (int i = 0; i < quantidadeAtual; i++) {
            if (alunos[i].getNome().equalsIgnoreCase(nome)) {
                alunosEncontrados.add(alunos[i]);
                if (apenasOPrimeiro) {
                    break;
                }
            }
        }

        return alunosEncontrados.toArray(new Aluno[0]);
    }


    public Aluno consultarPorMatricula(String matricula) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (alunos[i].getMatricula().equals(matricula)) {
                return alunos[i];
            }
        }

        return null;
    }


    public boolean excluirAluno(String nome) {
        int indiceRemocao = -1;

        // Localiza o índice do aluno a ser removido
        for (int i = 0; i < quantidadeAtual; i++) {
            if (alunos[i].getNome().equalsIgnoreCase(nome)) {
                indiceRemocao = i;
                break;
            }
        }


        if (indiceRemocao == -1) {
            System.out.println("Aluno com nome " + nome + " não encontrado.");
            return false;
        }


        for (int i = indiceRemocao; i < quantidadeAtual - 1; i++) {
            alunos[i] = alunos[i + 1];
        }

        alunos[quantidadeAtual - 1] = null;
        quantidadeAtual--;

        return true;
    }


    public void imprimirListaOriginal() {
        System.out.println("\n=== Lista de Alunos (Ordem de Inserção) ===");

        if (quantidadeAtual == 0) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }

        for (int i = 0; i < quantidadeAtual; i++) {
            System.out.println((i + 1) + ". " + alunos[i]);
        }
    }


    public void imprimirListaOrdenadaPorNome() {
        System.out.println("\n=== Lista de Alunos (Ordenada por Nome) ===");

        if (quantidadeAtual == 0) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }

        // Cria uma lista temporária para ordenação
        List<Aluno> listaOrdenada = new ArrayList<>();
        for (int i = 0; i < quantidadeAtual; i++) {
            listaOrdenada.add(alunos[i]);
        }

        // Ordena a lista por nome (usando o método compareTo implementado em Aluno)
        Collections.sort(listaOrdenada);

        // Imprime a lista ordenada
        for (int i = 0; i < listaOrdenada.size(); i++) {
            System.out.println((i + 1) + ". " + listaOrdenada.get(i));
        }
    }


    public boolean matriculaExiste(String matricula) {
        return consultarPorMatricula(matricula) != null;
    }


    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }


    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}