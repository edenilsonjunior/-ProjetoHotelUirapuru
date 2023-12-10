package model.payment;

import model.consumo.Consumo;
import model.lodging.Hospedagem;

import java.time.LocalDate;

public class Pagamento {
    private TipoPagamento opcao;
    private Hospedagem hospedagem;
    private Consumo consumo;
    private int numeroFatura;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double juros;
    private boolean status;

    public Pagamento(TipoPagamento opcao, Hospedagem hospedagem, Consumo consumo) {
        this.opcao = opcao;
        this.hospedagem = hospedagem;
        this.consumo = consumo;
        this.numeroFatura = 0;
        this.dataPagamento = null;
        this.juros = 10;
        this.status = false;
        if (opcao == TipoPagamento.DINHEIRO) {
            this.dataPagamento = hospedagem.getChegada().plusDays(1);
        }
        else if (opcao == TipoPagamento.CHEQUE) {
            this.dataPagamento = hospedagem.getChegada().plusDays(10);
        }
        else if (opcao == TipoPagamento.CREDITO) {
            this.dataPagamento = hospedagem.getChegada().plusDays(30);
        }
        else if (opcao == TipoPagamento.FATURADO) {
            this.dataPagamento = hospedagem.getSaida().plusDays(30);
        }
    }

    public TipoPagamento getOpcao() {
        return opcao;
    }

    public void setOpcao(TipoPagamento opcao) {
        this.opcao = opcao;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public int getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(int numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String fazerPagamento() {
        if (isStatus()) {
            return "Nome: " + hospedagem.getHospede().getNome() + "\n"
                    + "Pagamento realizado em: " + this.dataPagamento + "\n";
        } else {
            this.dataPagamento = LocalDate.now();
            this.dataVencimento = null;
            this.status = true;
            return "Nome: " + hospedagem.getHospede().getNome() + "\n"
                    + "Pagamento realizado com sucesso" + "\n";
        }
    }
}
