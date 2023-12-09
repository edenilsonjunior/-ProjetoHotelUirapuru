package model.consumo;

public class Consumo {
    private int codigo;
    private TipoConsumo tipo;
    private String descricao;
    private double valor;

    public Consumo(int codigo, TipoConsumo tipo, String descricao, double valor) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoConsumo getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsumo tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}


