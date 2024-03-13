package dto;

import model.consumo.*;

public class ConsumoDTO implements IDTO<Consumo>{
    
    private int codigo;
    private String tipo;
    private String descricao;
    private double valor;

    public ConsumoDTO(int codigo, String tipo, String descricao, double valor) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public ConsumoDTO(Consumo consumo){
        this.codigo = consumo.getCodigo();
        this.tipo = consumo.getTipo().name();
        this.descricao = consumo.getDescricao();
        this.valor = consumo.getValor();
    }

    @Override
    public Consumo toModelDomain() {
        return new Consumo(codigo, TipoConsumo.valueOf(tipo), descricao);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoConsumo getTipo() {
        return TipoConsumo.valueOf(tipo);
    }

    public void setTipo(TipoConsumo tipo) {
        this.tipo = tipo.name();
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
