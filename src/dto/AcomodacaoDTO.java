package dto;

import model.alojamento.Acomodacao;
import model.alojamento.TipoAcomodacao;

public class AcomodacaoDTO implements IDTO<Acomodacao>{

    private int codigo;
    private String tipoAcomodacao;
    private double diaria;
    private int maxAdultos;
    private int maxCriancas;
    private int andar;
    private int numeroQuarto;
    private boolean ocupado;

    public AcomodacaoDTO(int codigo, String TipoAcomodacao, double diaria, int maxAdultos, int maxCriancas, int andar, int numeroQuarto) {
        this.codigo = codigo;
        this.tipoAcomodacao = TipoAcomodacao;
        this.diaria = diaria;
        this.maxAdultos = maxAdultos;
        this.maxCriancas = maxCriancas;
        this.andar = andar;
        this.numeroQuarto = numeroQuarto;
        this.ocupado = false;
    }

    public AcomodacaoDTO(Acomodacao acomodacao) {
        this.codigo = acomodacao.getCodigo();
        this.tipoAcomodacao = acomodacao.getOpcao().name();
        this.diaria = acomodacao.getDiaria();
        this.maxAdultos = acomodacao.getMaxAdultos();
        this.maxCriancas = acomodacao.getMaxCriancas();
        this.andar = acomodacao.getAndar();
        this.numeroQuarto = acomodacao.getNumeroQuarto();
        this.ocupado = acomodacao.isOcupado();
    }

    @Override
    public Acomodacao toModelDomain() {
        return new Acomodacao(codigo, TipoAcomodacao.valueOf(tipoAcomodacao), diaria, maxAdultos, maxCriancas, andar, numeroQuarto);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoAcomodacao() {
        return tipoAcomodacao;
    }

    public void setTipoAcomodacao(String tipoAcomodacao) {
        this.tipoAcomodacao = tipoAcomodacao;
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
