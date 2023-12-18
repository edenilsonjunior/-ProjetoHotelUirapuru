package model.pessoa;

public class Acompanhante {

    private String nome;
    private int idade;


    /**
     * Construtor da classe Acompanhante.
     *
     * @param nome O nome do acompanhante.
     * @param idade A idade do acompanhante.
     */
    public Acompanhante(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}