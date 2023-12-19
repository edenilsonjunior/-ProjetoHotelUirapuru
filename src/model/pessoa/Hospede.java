package model.pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.consumo.Consumo;

/**
 * Classe que representa um hóspede.
 */
public class Hospede extends Pessoa {

    private String pais;
    private String email;
    private int identificacao;
    private String nomePai;
    private String nomeMae;
    private int dadosCartao;
    private List<Consumo> listaConsumo;
    private List<Acompanhante> acompanhantes;


    /**
     * Construtor da classe Hospede.
     *
     * @param pais O país de origem do hóspede.
     * @param email O endereço de e-mail do hóspede.
     * @param identificacao O número de identificação do hóspede.
     * @param nomePai O nome do pai do hóspede.
     * @param nomeMae O nome da mãe do hóspede.
     * @param dadosCartao Os dados do cartão do hóspede.
     * @param nome O nome do hóspede.
     * @param endereco O endereço do hóspede.
     * @param cidade A cidade do hóspede.
     * @param estado O estado do hóspede.
     * @param telefone O número de telefone do hóspede.
     * @param dataNascimento A data de nascimento do hóspede.
     */
    public Hospede(String pais, String email, int identificacao, String nomePai, String nomeMae, int dadosCartao, String nome, String endereco, String cidade, String estado, String telefone, LocalDate dataNascimento) {
        super(nome, endereco, cidade, estado, telefone, dataNascimento);
        this.pais = pais;
        this.email = email;
        this.identificacao = identificacao;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.dadosCartao = dadosCartao;

        listaConsumo = new ArrayList<>();
        acompanhantes = new ArrayList<>();
    }


    /**
     * Método que retorna a descrição do hóspede.
     *
     * @return Uma string contendo informações detalhadas sobre o hóspede.
     */
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

        if (!acompanhantes.isEmpty()) {
            descricao += "Acompanhante(s):\n";
            for (Acompanhante acompanhante : acompanhantes) {
                descricao += acompanhante.getNome() + ", " + acompanhante.getIdade() + "\n";
            }
        }
        
        return descricao;
    }


    /**
     * Gera um relatório de consumo para todos os itens na lista.
     *
     * @return Uma string contendo o relatório de consumo.
     */
    public String relatorioConsumo() {
        StringBuilder relatorio = new StringBuilder();
        for (Consumo consumo : listaConsumo) {
            relatorio.append(consumo.getDescricao()).append("\n");
        }
        return relatorio.toString();
    }


    /**
     * Adiciona um objeto de Consumo à lista.
     *
     * @param consumo Objeto de Consumo a ser adicionado.
     * @throws NullPointerException Se 'consumo' for nulo.
     */
    public void addConsumo(Consumo consumo) {
        listaConsumo.add(consumo);
    }


    /**
     * Remove um objeto de Consumo da lista.
     *
     * @param consumo Objeto de Consumo a ser removido.
     */
    public void removerConsumo(Consumo consumo) {
        listaConsumo.remove(consumo);
    }


    /**
     * Calcula o total de valores de consumo na lista.
     * Este método itera sobre a lista de consumos e calcula a soma total dos valores.
     *
     * @return O total dos valores de consumo.
     */
    public double totalConsumo() {
        double total = 0;
        for (Consumo consumo : listaConsumo) {
            total += consumo.getValor();
        }
        return total;
    }


    /**
     * Adiciona um objeto Acompanhante à lista de acompanhantes.
     *
     * @param acompanhante Objeto de Acompanhante a ser adicionado.
     */
    public void addAcompanhante(Acompanhante acompanhante) {
        acompanhantes.add(acompanhante);
    }


    /**
     * Remove um objeto Acompanhante da lista de acompanhantes.
     *
     * @param acompanhante Objeto de Acompanhante a ser removido.
     */
    public void removeAcompanhante(Acompanhante acompanhante) {
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

    public List<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }
}
