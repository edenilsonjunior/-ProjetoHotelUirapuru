package dto;

import java.time.LocalDate;

import model.pessoa.Funcionario;

public class FuncionarioDTO implements IDTO<Funcionario>{

    private int codigo;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;
    private LocalDate dataNascimento;
    
    public FuncionarioDTO(int codigo, String nome, String endereco, String cidade, String estado, String telefone, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public FuncionarioDTO(Funcionario Funcionario){
        this.codigo = Funcionario.getCodigo();
        this.nome = Funcionario.getNome();
        this.endereco = Funcionario.getEndereco();
        this.cidade = Funcionario.getCidade();
        this.estado = Funcionario.getEstado();
        this.telefone = Funcionario.getTelefone();
        this.dataNascimento = Funcionario.getDataNascimentoDate();
    }

    @Override
    public Funcionario toModelDomain() {
        return new Funcionario(codigo, nome, endereco, cidade, estado, telefone, dataNascimento);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
}
