package model.people;

public class Funcionario extends Pessoa{
    
    private int codigo;

    public Funcionario(int codigo, String nome, String endereco, String cidade, String estado, int telefone, String dataNascimento) {
        super(nome, endereco, cidade, estado, telefone, dataNascimento);
        this.codigo = codigo;
    }

    public Funcionario(int codigo, String nome){
        super(nome);
        this.codigo = codigo;
    }

    @Override
    public String getDescricao() {
        return "Nome: " + getNome() + ", Código: " + getCodigo() + ", Cargo: Funcionário";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}