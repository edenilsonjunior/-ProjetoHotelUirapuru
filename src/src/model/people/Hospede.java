package model.people;

import java.util.ArrayList;
import java.util.List;

import model.consumption.Consumo;

public class Hospede extends Pessoa {
    private String pais;
    private String email;
    private int identificacao;
    private String nomePai;
    private String nomeMae;
    private int dadosCartao;
    private List<Consumo> listaConsumo;
    private List<String> acompanhantes;

    public Hospede(String pais, String email, int identificacao, String nomePai, String nomeMae, int dadosCartao, String nome, String endereco, String cidade, String estado, int telefone, String dataNascimento) {
        super(nome, endereco, cidade, estado, telefone, dataNascimento);
        this.pais = pais;
        this.email = email;
        this.identificacao = identificacao;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.dadosCartao = dadosCartao;

        listaConsumo = new ArrayList<Consumo>();
        acompanhantes = new ArrayList<String>();
    }

    @Override
    public String getDescricao() {
        String descricao = "";

        descricao += "Nome: " + getNome() + "\n";
        descricao += "Endereco: " + getEndereco() + "\n";
        descricao += "Cidade: " + getCidade() + "\n";
        descricao += "Estado: " + getEstado() + "\n";
        descricao += "Telefone: " + getTelefone() + "\n";
        descricao += "Data de Nascimento: " + getDataNascimento() + "\n";
        descricao += "Pais: " + pais + "\n";
        descricao += "Email: " + email + "\n";
        descricao += "Identificacao: " + identificacao + "\n";
        descricao += "Nome do Pai: " + nomePai + "\n";
        descricao += "Nome da Mae: " + nomeMae + "\n";
        descricao += "Dados do Cartao: " + dadosCartao + "\n";

        for (String acompanhante : acompanhantes) {
            descricao += "Acompanhante: " + acompanhante + "\n";
        }
        
        return descricao;
    }

    /**
     * Metodo que retorna um relatorio com os consumos do hospede.
     * @return String contendo os consumos do hospede.
     */
    public String relatorioConsumo() {
        StringBuilder relatorio = new StringBuilder();
        for (Consumo consumo : listaConsumo) {
            relatorio.append(consumo.getDescricao()).append("\n");
        }
        return relatorio.toString();
    }

    /**
     * Metodo que adiciona um consumo a lista de consumos do hospede.
     */
    public void adicionarConsumo(Consumo consumo) {
        listaConsumo.add(consumo);
    }

    /**
     * Metodo que remove um consumo da lista de consumos do hospede.
     */
    public void removerConsumo(Consumo consumo) {
        listaConsumo.remove(consumo);
    }

    /**
     * Metodo que retorna um calculo do total dos consumos do hospede.
     * @return double contendo o total dos consumos do hospede.
     */
    public double totalConsumo() {
        double total = 0;
        for (Consumo consumo : listaConsumo) {
            total += consumo.getValor();
        }
        return total;
    }

    public void addAcompanhante(String acompanhante) {
        acompanhantes.add(acompanhante);
    }

    public void removeAcompanhante(String acompanhante) {
        acompanhantes.remove(acompanhante);
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

    public List<Consumo> getListaConsumo() {
        return listaConsumo;
    }

    public void setListaConsumo(List<Consumo> listaConsumo) {
        this.listaConsumo = listaConsumo;
    }

    public List<String> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(List<String> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

}
