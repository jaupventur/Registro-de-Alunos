package modelo;


public class Aluno implements Comparable<Aluno> {
    private String matricula;
    private String nome;
    private String curso;
    private modelo.Prova[] provas;


    public Aluno(String matricula, String nome, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.provas = new modelo.Prova[4]; // 4 provas no semestre

        // Inicializa as provas com pesos diferentes
        for (int i = 0; i < 4; i++) {
            this.provas[i] = new modelo.Prova(0.0, (i + 1) * 0.5); // Pesos: 0.5, 1.0, 1.5, 2.0
        }
    }


    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCurso() {
        return curso;
    }


    public void setCurso(String curso) {
        this.curso = curso;
    }


    public modelo.Prova[] getProvas() {
        return provas;
    }


    public void setNotaProva(int numeroProva, double nota) {
        if (numeroProva >= 1 && numeroProva <= 4) {
            this.provas[numeroProva - 1].setNota(nota);
        }
    }


    public double calcularMedia() {
        double somaProdutos = 0.0;
        double somaPesos = 0.0;

        for (modelo.Prova prova : provas) {
            somaProdutos += prova.getNota() * prova.getPeso();
            somaPesos += prova.getPeso();
        }

        return somaPesos > 0 ? somaProdutos / somaPesos : 0.0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno: ").append(nome);
        sb.append(" | Matrícula: ").append(matricula);
        sb.append(" | Curso: ").append(curso);
        sb.append(" | Notas: [");

        for (int i = 0; i < provas.length; i++) {
            sb.append(provas[i].getNota());
            if (i < provas.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("] | Média: ").append(String.format("%.2f", calcularMedia()));

        return sb.toString();
    }


    @Override
    public int compareTo(Aluno outroAluno) {
        return this.nome.compareToIgnoreCase(outroAluno.getNome());
    }
}