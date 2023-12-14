package model.lodging;

public class Acomodacao {

    private int codigo;
    private TipoAcomodacao opcao;
    private int totalRestante;
    private double precoDiaria;
    private int maxAdultos;
    private int maxCriancas;
    private int andar;
    private int numeroQuarto;

    /**
     * Metodo construtor da classe Acomodacao
     *
     * @param codigo codigo da acomodacao
     * @param opcao tipo da acomodacao
     * @param totalRestante total de acomodacoes restantes
     * @param precoDiaria preco da diaria
     * @param maxAdultos numero maximo de adultos
     * @param maxCriancas numero maximo de criancas
     * @param andar andar da acomodacao
     * @param numeroQuarto numero do quarto
     */
    public Acomodacao(int codigo, TipoAcomodacao opcao, int totalRestante, double precoDiaria, int maxAdultos, int maxCriancas, int andar, int numeroQuarto) {
        this.codigo = codigo;
        this.opcao = opcao;
        this.totalRestante = totalRestante;
        this.precoDiaria = precoDiaria;
        this.maxAdultos = maxAdultos;
        this.maxCriancas = maxCriancas;
        this.andar = andar;
        this.numeroQuarto = numeroQuarto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoAcomodacao getOpcao() {
        return opcao;
    }

    public void setOpcao(TipoAcomodacao opcao) {
        this.opcao = opcao;
    }

    public int getTotalRestante() {
        return totalRestante;
    }

    public void setTotalRestante(int totalRestante) {
        this.totalRestante = totalRestante;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public int getMaxAdultos() {
        return maxAdultos;
    }

    public void setMaxAdultos(int maxAdultos) {
        this.maxAdultos = maxAdultos;
    }

    public int getMaxCriancas() {
        return maxCriancas;
    }

    public void setMaxCriancas(int maxCriancas) {
        this.maxCriancas = maxCriancas;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

}
