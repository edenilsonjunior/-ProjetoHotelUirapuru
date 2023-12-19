package model.alojamento;


/**
 * Classe que representa uma acomodação do hotel.
 */
public class Acomodacao {

    private int codigo;
    private TipoAcomodacao opcao;
    private double diaria;
    private int maxAdultos;
    private int maxCriancas;
    private int andar;
    private int numeroQuarto;
    private boolean ocupado;

    /**
     * Construtor da classe Acomodacao para inicialização dos atributos.
     *
     * @param codigo       O código da acomodação.
     * @param opcao        O tipo de acomodação.
     * @param diaria       O valor da diária.
     * @param maxAdultos   O número máximo de adultos permitidos na acomodação.
     * @param maxCriancas  O número máximo de crianças permitidas na acomodação.
     * @param andar        O andar da acomodação.
     * @param numeroQuarto O número do quarto.
     */
    public Acomodacao(int codigo, TipoAcomodacao opcao, double diaria, int maxAdultos, int maxCriancas, int andar, int numeroQuarto) {
        this.codigo = codigo;
        this.opcao = opcao;
        this.diaria = diaria;
        this.maxAdultos = maxAdultos;
        this.maxCriancas = maxCriancas;
        this.andar = andar;
        this.numeroQuarto = numeroQuarto;
        this.ocupado = false;
    }


    /**
     * Gera um relatório da acomodação, incluindo informações como código, tipo, preço da diária,
     * número máximo de adultos, número máximo de crianças, andar, número do quarto e status de ocupação.
     *
     * @return Uma String contendo o relatório da acomodação.
     */
    public String relatorioAcomodacao() {

        String descricao = ""; 
        descricao += "Código: " + getCodigo() + "\n";
        descricao += "Tipo: " + getOpcao() + "\n";
        descricao += "Preço da diária: " + getDiaria() + "\n";
        descricao += "Número máximo de adultos: " + getMaxAdultos() + "\n";
        descricao += "Número máximo de crianças: " + getMaxCriancas() + "\n";
        descricao += "Andar: " + getAndar() + "\n";
        descricao += "Número do quarto: " + getNumeroQuarto() + "\n";
        descricao += "Status de ocupação: " + isOcupado() + "\n";

        return descricao;
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

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
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

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }   

}
