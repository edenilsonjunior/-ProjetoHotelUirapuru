package dto;

import model.pessoa.Acompanhante;

public class AcompanhanteDTO implements IDTO<Acompanhante>{
    
    private String nome;
    private int idade;

    public AcompanhanteDTO(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public AcompanhanteDTO(Acompanhante acompanhante) {
        this.nome = acompanhante.getNome();
        this.idade = acompanhante.getIdade();
    }

    @Override
    public Acompanhante toModelDomain() {
        return new Acompanhante(nome, idade);
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
