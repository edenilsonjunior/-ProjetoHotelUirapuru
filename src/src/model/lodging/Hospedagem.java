package model.lodging;

import java.time.LocalDate;
import java.util.List;

public class Hospedagem {

    private Acomodacao acomodacao;
    private LocalDate chegada;
    private LocalDate saida;
    // private Hospede hospede;
    private int codigo;
    private double multa;
    private boolean status;

    public Hospedagem(Acomodacao acomodacao, LocalDate chegada, LocalDate saida, int codigo, boolean status) {
        this.acomodacao = acomodacao;
        this.chegada = chegada;
        this.saida = saida;
        this.codigo = codigo;
        this.multa = 0;
        this.status = status; // se true = hospedagem, se false = reserva
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

    public void removerReserva(List list, Hospedagem hospedagem) {
        if (!hospedagem.isStatus()) {
            list.remove(hospedagem);
        }
    }

    public void removerHospedagem(List list, Hospedagem hospedagem) {
        if (hospedagem.isStatus()) {
            list.remove(hospedagem);
        }
    }

    public double totalDiarias() {
        if (isStatus()) {
            return acomodacao.getPrecoDiaria() * (saida.getDayOfYear() - chegada.getDayOfYear());
        } else {
            return 0;
        }
    }

    // implemente um relatorio de hospedagem

    public String relatorioHospedagem() {
        return "Código: " + getCodigo() + "\n"
                + "Descrição: " + getAcomodacao().getDescricao() + "\n"
                + "Total de diárias: " + (saida.getDayOfYear() - chegada.getDayOfYear()) + "\n"
                + "Preço da diária: " + getAcomodacao().getPrecoDiaria() + "\n"
                + "Total: " + totalDiarias() + "\n"
                + "Multa: " + getMulta() + "\n"
                + "Total a pagar: " + (totalDiarias() + getMulta()) + "\n";
    } // falta adicionar o hospede
}
