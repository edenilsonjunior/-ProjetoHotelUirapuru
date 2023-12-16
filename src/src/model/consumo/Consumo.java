package model.consumo;

public class Consumo {
    private int codigo;
    private TipoConsumo tipo;
    private String descricao;
    private double valor;

    public Consumo(int codigo, TipoConsumo tipo, String descricao) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;

        if (tipo == TipoConsumo.RESTAURANTE) {
            this.valor = 50;
        }
        else if (tipo == TipoConsumo.FRIGOBAR) {
            this.valor = 20;
        }
        else if (tipo == TipoConsumo.LAVANDERIA) {
            this.valor = 30;
        }
        else if (tipo == TipoConsumo.TELEFONE) {
            this.valor = 10;
        }
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


