package model.pessoa;

public abstract class Pessoa {

    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private int telefone;
    private String dataNascimento;
    private String login;
    private String senha;

    /**
     * Construtor da classe Pessoa.
     *
     * @param nome O nome da pessoa.
     * @param endereco O endereço da pessoa.
     * @param cidade A cidade da pessoa.
     * @param estado O estado da pessoa.
     * @param telefone O número de telefone da pessoa.
     * @param dataNascimento A data de nascimento da pessoa.
     */
    public Pessoa(String nome, String endereco, String cidade, String estado, int telefone, String dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.login = nome;
        this.senha = nome + "123";
    }

    /**
     * Construtor da classe Pessoa.
     * Cria admin
     *
     * @param nome O nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
        this.login = nome;
        this.senha = nome + "123";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Método abstrato para obter a descrição da entidade.
     *
     * @return Uma string contendo a descrição da entidade.
     */
    public abstract String getDescricao();
}
