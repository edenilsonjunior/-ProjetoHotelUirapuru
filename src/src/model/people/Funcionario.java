package model.people;

public class Funcionario extends Pessoa{
    
    private int codigo;
    private String login;
    private String senha;

    public Funcionario(int codigo, String login, String senha, String nome, String endereco, String cidade, String estado, int telefone, String dataNascimento) {
        super(nome, endereco, cidade, estado, telefone, dataNascimento);
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getDescricao() {
        return "Nome: " + getNome() + ", Código: " + getCodigo() + ", Cargo: Funcionário";
    }
}
