package model.alojamento;

import java.time.LocalDate;

import model.pessoa.Hospede;

public class Hospedagem {

    private Acomodacao acomodacao;
    private LocalDate chegada;
    private LocalDate saida;
    private Hospede hospede;
    private int codigo;
    private double multa;
    private boolean status;

    /**
     * Metodo construtor da classe Hospedagem, se o status for definido como true é uma hospedagem, se for false é uma reserva
     *
     * @param acomodacao dados da classe acomodacao
     * @param hospede dados da classe hospede
     * @param chegada data de chegada
     * @param saida data de saida
     * @param codigo codigo da hospedagem
     * @param status status da hospedagem ou reserva
     */
    public Hospedagem(Acomodacao acomodacao, Hospede hospede, LocalDate chegada, LocalDate saida, int codigo, boolean status) {
        this.acomodacao = acomodacao;
        this.hospede = hospede;
        this.chegada = chegada;
        this.saida = saida;
        this.codigo = codigo;
        this.multa = 0;
        this.status = status;

        this.acomodacao.setOcupado(true);
    }

    /**
     * Metodo que calcula o total de diarias
     *
     * @return total de diarias
     */
    public double totalDiarias() {
        if (isStatus()) {
            return acomodacao.getDiaria() * (saida.getDayOfYear() - chegada.getDayOfYear());
        } else {
            return 0;
        }
    }

    /**
     * Metodo que gera um relatorio de hospedagem
     *
     * @return relatorio de hospedagem
     */
    public String relatorioHospedagem() {
        return "Nome: " + getHospede().getNome() + "\n"
                + "Código: " + getCodigo() + "\n"
                + "Descrição: " + getAcomodacao().getOpcao() + "\n"
                + "Total de diárias: " + (saida.getDayOfYear() - chegada.getDayOfYear()) + "\n"
                + "Preço da diária: " + getAcomodacao().getDiaria() + "\n"
                + "Total: " + totalDiarias() + "\n"
                + "Multa: " + getMulta() + "\n"
                + "Total a pagar: " + (totalDiarias() + getMulta()) + "\n";
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public LocalDate getChegada() {
        return chegada;
    }

    public void setChegada(LocalDate chegada) {
        this.chegada = chegada;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}