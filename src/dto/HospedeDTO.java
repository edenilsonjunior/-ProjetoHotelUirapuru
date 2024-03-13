package dto;

import java.time.LocalDate;

import model.pessoa.Hospede;

public class HospedeDTO implements IDTO<Hospede>{

    private String pais;
    private String email;
    private int identificacao;
    private String nomePai;
    private String nomeMae;
    private int dadosCartao;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;
    private LocalDate dataNascimento;

    public HospedeDTO(String pais, String email, int identificacao, String nomePai, String nomeMae, int dadosCartao, String nome, String endereco, String cidade, String estado, String telefone, LocalDate dataNascimento){

        this.pais = pais;
        this.email = email;
        this.identificacao = identificacao;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.dadosCartao = dadosCartao;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }


    public HospedeDTO (Hospede hospede) {
        this.pais = hospede.getPais();
        this.email = hospede.getEmail();
        this.identificacao = hospede.getIdentificacao();
        this.nomePai = hospede.getNomePai();
        this.nomeMae = hospede.getNomeMae();
        this.dadosCartao = hospede.getDadosCartao();
        this.nome = hospede.getNome();
        this.endereco = hospede.getEndereco();
        this.cidade = hospede.getCidade();
        this.estado = hospede.getEstado();
        this.telefone = hospede.getTelefone();
        this.dataNascimento = hospede.getDataNascimentoDate();
    }

    @Override
    public Hospede toModelDomain() {
        return new Hospede(pais, email, identificacao, nomePai, nomeMae, dadosCartao, nome, endereco, cidade, estado, telefone, dataNascimento);
    }


    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getIdentificacao() {
        return identificacao;
    }


    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }


    public String getNomePai() {
        return nomePai;
    }


    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }


    public String getNomeMae() {
        return nomeMae;
    }


    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }


    public int getDadosCartao() {
        return dadosCartao;
    }


    public void setDadosCartao(int dadosCartao) {
        this.dadosCartao = dadosCartao;
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
