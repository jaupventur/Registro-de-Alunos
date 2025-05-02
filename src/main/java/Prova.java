package modelo;

/**
 * Classe que representa uma prova
 */
public class Prova {
    private double nota;
    private double peso;

    /**
     * Construtor da classe Prova
     * @param nota Nota da prova
     * @param peso Peso da prova
     */
    public Prova(double nota, double peso) {
        this.nota = nota;
        this.peso = peso;
    }

    /**
     * Retorna a nota da prova
     * @return Nota da prova
     */
    public double getNota() {
        return nota;
    }

    /**
     * Define a nota da prova
     * @param nota Nova nota
     */
    public void setNota(double nota) {
        // Validação para garantir que a nota esteja entre 0 e 10
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
        } else {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10");
        }
    }

    /**
     * Retorna o peso da prova
     * @return Peso da prova
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Define o peso da prova
     * @param peso Novo peso
     */
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        } else {
            throw new IllegalArgumentException("O peso deve ser maior que zero");
        }
    }
}